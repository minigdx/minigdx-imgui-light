package com.github.minigdx.imgui

import com.github.minigdx.imgui.atlas.DefaultWidgetAtlas
import com.github.minigdx.imgui.atlas.WidgetAtlas
import com.github.minigdx.imgui.internal.Cursor
import com.github.minigdx.imgui.internal.Quad
import com.github.minigdx.imgui.internal.Resolution
import com.github.minigdx.imgui.internal.UVCoordinates
import kotlin.math.abs

// https://github.com/matson48/matson48-fonts
// https://github.com/bluescan/proggyfonts
// TODO: texture atlas hardcodé

fun defaultWidgetAtlas(): WidgetAtlas {
    return DefaultWidgetAtlas()
}

@DslMarker
annotation class ImGUI

fun <TEXTURE> gui(
    renderer: ImGUIRenderer<TEXTURE>,
    defaultTexture: TEXTURE,
    inputCapture: InputCapture,
    gameResolution: Resolution,
    widgetBuilder: WidgetAtlas = defaultWidgetAtlas(),
    builder: WidgetBuilder<TEXTURE>.() -> Unit
) {
    inputCapture.update()
    val widgetDsl = WidgetDsl(defaultTexture, inputCapture, gameResolution, widgetBuilder)
    widgetDsl.builder()
    widgetDsl.batchs.values.forEach { batch ->
        renderer.render(
            batch.texture,
            batch.vertices.toFloatArray(),
            batch.uvs.toFloatArray(),
            batch.verticesOrder.toIntArray()
        )
    }
}

interface WidgetBuilder<TEXTURE> {

    fun horizontalContainer(
        x: Float,
        y: Float,
        width: Float,
        builder: WidgetDsl<TEXTURE>.ContainerDsl.() -> Unit
    )

    fun verticalContainer(
        x: Float = 0f,
        y: Float = 0f,
        width: Float = 1f,
        builder: WidgetDsl<TEXTURE>.ContainerDsl.() -> Unit
    )
}

class TextureDescription<TEXTURE>(val texture: TEXTURE, val with: Int, val height: Int)

class Batch<TEXTURE>(
    val vertices: ArrayList<Float> = arrayListOf(),
    val uvs: ArrayList<Float> = arrayListOf(),
    val verticesOrder: ArrayList<Int> = arrayListOf(),
    val texture: TEXTURE
)

@ImGUI
class WidgetDsl<TEXTURE>(
    private val defaultTexture: TEXTURE,
    private val inputCapture: InputCapture,
    private val gameResolution: Resolution,
    private val widgetAtlas: WidgetAtlas
) : WidgetBuilder<TEXTURE> {

    private val defaultBatch = Batch(texture = defaultTexture)
    private val defaultQuad = Quad(defaultBatch)

    val batchs = mutableMapOf<TEXTURE, Batch<TEXTURE>>().also {
        it.put(defaultTexture, defaultBatch)
    }

    private val quads = mutableMapOf<TEXTURE, Quad>().also {
        it.put(defaultTexture, defaultQuad)
    }

    override fun horizontalContainer(
        x: Float,
        y: Float,
        width: Float,
        builder: ContainerDsl.() -> Unit
    ) {
        val cursor = Cursor(Cursor.CursorWay.HORIZONTAL, x, y)
        ContainerDsl(cursor, width).builder()
    }

    override fun verticalContainer(
        x: Float,
        y: Float,
        width: Float,
        builder: ContainerDsl.() -> Unit
    ) {
        val cursor = Cursor(Cursor.CursorWay.VERTICAL, x, y)
        ContainerDsl(cursor, width).builder()
    }

    inner class ContainerDsl(
        private val cursor: Cursor,
        private val width: Float
    ) {

        fun button(
            x: Float = 0f,
            width: Float = 1f,
            label: String = "",
            isPushed: Boolean = false,
            onClicked: () -> Unit = {}
        ) {

            val height =
                (abs(widgetAtlas.buttonOver.left.second.y - widgetAtlas.buttonOver.left.first.y) * widgetAtlas.resolution.height) / gameResolution.height

            val rectX = cursor.x + this@ContainerDsl.width * x
            val rectY = cursor.y
            val with = this@ContainerDsl.width * width

            val mouseOver = isMouseOver(rectX, rectY, with, height)

            val uvsLeft = if (isPushed) {
                widgetAtlas.buttonActivated.left
            } else {
                if (mouseOver) {
                    widgetAtlas.buttonOver.left
                } else {
                    widgetAtlas.buttonIdle.left
                }
            }

            // button size
            val borderWidth =
                (abs(uvsLeft.second.x - uvsLeft.first.x) * widgetAtlas.resolution.width) / gameResolution.width
            val borderHeight =
                (abs(uvsLeft.second.y - uvsLeft.first.y) * widgetAtlas.resolution.height) / gameResolution.height

            defaultQuad.append(cursor.x + this@ContainerDsl.width * x, cursor.y, borderWidth, borderHeight, uvsLeft)

            val uvsCenter = if (isPushed) {
                widgetAtlas.buttonActivated.center
            } else {
                if (mouseOver) {
                    widgetAtlas.buttonOver.center
                } else {
                    widgetAtlas.buttonIdle.center
                }
            }
            // TODO: manage parent width too.
            defaultQuad.append(
                cursor.x + this@ContainerDsl.width * x + borderWidth,
                cursor.y,
                (this@ContainerDsl.width * width) - 2 * borderWidth,
                borderHeight,
                uvsCenter
            )

            val uvsRight = if (isPushed) {
                widgetAtlas.buttonActivated.right
            } else {
                if (mouseOver) {
                    widgetAtlas.buttonOver.right
                } else {
                    widgetAtlas.buttonIdle.right
                }
            }
            defaultQuad.append(
                cursor.x + this@ContainerDsl.width * x + (this@ContainerDsl.width * width) - borderWidth,
                cursor.y,
                borderWidth,
                borderHeight,
                uvsRight
            )

            drawText(
                cursor.x + this@ContainerDsl.width * x + borderWidth,
                cursor.y + 1 / gameResolution.height,
                label
            )

            if (mouseOver && inputCapture.isTouch) {
                onClicked()
            }

            cursor.advance(this@ContainerDsl.width * width, borderHeight)
        }

        private fun isMouseOver(x: Float, y: Float, with: Float, height: Float): Boolean {
            val xx = inputCapture.x / gameResolution.width
            val yy = inputCapture.y / gameResolution.height
            return (x <= xx && xx <= x + with) && (y <= yy && yy <= y + height)
        }

        fun label(
            x: Float,
            y: Float,
            with: Float,
            height: Float,
            name: String,
            value: String,
        ) {
            TODO("Not yet implemented")
        }

        fun texture(
            x: Float = 0f,
            width: Float = 1f,
            texture: TextureDescription<TEXTURE>
        ) {
            val quad = quads.getOrPut(texture.texture) {
                Quad(batchs.getOrPut(texture.texture) { Batch(texture = texture.texture) })
            }

            val ratio = texture.height / texture.with.toFloat()
            val resolution = Resolution(texture.with, texture.height)
            quad.append(
                cursor.x + this@ContainerDsl.width * x,
                cursor.y,
                this@ContainerDsl.width * width,
                this@ContainerDsl.width * width * ratio,
                UVCoordinates(0, 0, resolution) to (UVCoordinates(texture.with, texture.height, resolution))
            )

            cursor.advance(this@ContainerDsl.width * width, this@ContainerDsl.width * width * ratio)
        }

        private fun drawText(x: Float, y: Float, text: String) {
            var currentX = x
            var currentY = y
            text.forEach {
                val uvs = widgetAtlas.text.letter(it)
                if (it == '\n') {
                    currentY += (uvs.height * widgetAtlas.resolution.height) / gameResolution.height
                    currentX = x
                } else {
                    defaultQuad.append(
                        currentX,
                        currentY,
                        (uvs.width * widgetAtlas.resolution.width) / gameResolution.width,
                        (uvs.height * widgetAtlas.resolution.height) / gameResolution.height,
                        uvs
                    )
                    currentX += (uvs.width * widgetAtlas.resolution.width) / gameResolution.width
                }
            }
        }
    }

    val Pair<UVCoordinates, UVCoordinates>.width: Float
        get() = abs(this.first.x - this.second.x)

    val Pair<UVCoordinates, UVCoordinates>.height: Float
        get() = abs(this.first.y - this.second.y)
}
