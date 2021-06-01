package com.github.minigdx.imgui

import com.github.minigdx.imgui.atlas.DefaultWidgetAtlas
import com.github.minigdx.imgui.atlas.WidgetAtlas
import com.github.minigdx.imgui.internal.Cursor
import com.github.minigdx.imgui.internal.Quad
import com.github.minigdx.imgui.internal.Resolution
import kotlin.math.abs

// https://github.com/matson48/matson48-fonts
// https://github.com/bluescan/proggyfonts
// TODO: texture atlas hardcodé

fun defaultWidgetAtlas(): WidgetAtlas {
    return DefaultWidgetAtlas()
}

@DslMarker
annotation class ImGUI

fun gui(
    renderer: ImGUIRenderer,
    inputCapture: InputCapture,
    gameResolution: Resolution,
    widgetBuilder: WidgetAtlas = defaultWidgetAtlas(),
    builder: WidgetDsl.() -> Unit
) {
    inputCapture.update()
    val widgetDsl = WidgetDsl(inputCapture, gameResolution, widgetBuilder)
    widgetDsl.builder()
    renderer.render(
        widgetDsl.vertices.toFloatArray(),
        widgetDsl.uvs.toFloatArray(),
        widgetDsl.verticesOrder.toIntArray()
    )
}

@ImGUI
class WidgetDsl(
    private val inputCapture: InputCapture,
    private val gameResolution: Resolution,
    private val widgetAtlas: WidgetAtlas
) {

    internal val vertices: ArrayList<Float> = arrayListOf()
    internal val uvs: ArrayList<Float> = arrayListOf()
    internal val verticesOrder: ArrayList<Int> = arrayListOf()

    private val quad = Quad(vertices, verticesOrder, uvs)

    fun horizontalContainer(
        x: Float,
        y: Float,
        width: Float,
        builder: ContainerDsl.() -> Unit
    ) {
        val cursor = Cursor(Cursor.CursorWay.HORIZONTAL, x, y)
        ContainerDsl(cursor, width).builder()
    }

    fun verticalContainer(
        x: Float = 0f,
        y: Float = 0f,
        width: Float = 1f,
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
            val mouseOver = isMouseOver(
                cursor.x + this@ContainerDsl.width * x,
                cursor.y,
                this@ContainerDsl.width * width,
                height
            )

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

            quad.append(cursor.x + this@ContainerDsl.width * x, cursor.y, borderWidth, borderHeight, uvsLeft)

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
            quad.append(
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
            quad.append(
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
                    quad.append(
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
}

val Pair<UVCoordinates, UVCoordinates>.width: Float
    get() = abs(this.first.x - this.second.x)

val Pair<UVCoordinates, UVCoordinates>.height: Float
    get() = abs(this.first.y - this.second.y)
