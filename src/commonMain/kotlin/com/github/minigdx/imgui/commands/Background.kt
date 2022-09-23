package com.github.minigdx.imgui.commands

import com.github.minigdx.imgui.ImGui
import com.github.minigdx.imgui.atlas.DefaultButtonAtlas
import com.github.minigdx.imgui.atlas.WidgetAtlas
import com.github.minigdx.imgui.internal.Cursor
import com.github.minigdx.imgui.internal.Quad

class Background(
    private val type: Type,
    private val width: Float,
    private val height: Float = Cursor.LINE_HEIGHT,
    private val dark: Boolean = false,
) : Primitive {

    enum class Type {
        HEADER,
        BODY,
        FOOTER,
        BUTTON,
        BUTTON_OVER,
        BUTTON_PUSHED,
    }

    // It's a background element.
    // It will not affect the cursor.
    override fun advance(cursor: Cursor) = Unit

    override val texture: Any = ImGui.atlas

    override val quads: List<Quad> = createQuads()

    private fun createQuads(): List<Quad> {

        val atlas = if (dark) {
            when (type) {
                Type.HEADER -> WidgetAtlas.DEFAULT.containerTopDark
                Type.BODY -> WidgetAtlas.DEFAULT.containerBodyDark
                Type.FOOTER -> WidgetAtlas.DEFAULT.containerBottomDark
                Type.BUTTON -> WidgetAtlas.DEFAULT.buttonIdle
                Type.BUTTON_OVER -> WidgetAtlas.DEFAULT.buttonOver
                Type.BUTTON_PUSHED -> WidgetAtlas.DEFAULT.buttonActivated
            }
        } else {
            when (type) {
                Type.HEADER -> WidgetAtlas.DEFAULT.containerTopClear
                Type.BODY -> WidgetAtlas.DEFAULT.containerBodyClear
                Type.FOOTER -> WidgetAtlas.DEFAULT.containerBottomClear
                Type.BUTTON -> WidgetAtlas.DEFAULT.buttonIdle
                Type.BUTTON_OVER -> WidgetAtlas.DEFAULT.buttonOver
                Type.BUTTON_PUSHED -> WidgetAtlas.DEFAULT.buttonActivated
            }
        }

        val left = atlas.left
        val center = atlas.center
        val right = atlas.right
        val fillWidth = DefaultButtonAtlas.BORDER_WIDTH.toFloat()

        val l = Quad().append(0f, 0f, fillWidth, height, left)
        val c = Quad().append(fillWidth, 0f, width - fillWidth * 2f, height, center)
        val r = Quad().append(width - fillWidth, 0f, fillWidth, height, right)
        return listOf(l, c, r)
    }
}
