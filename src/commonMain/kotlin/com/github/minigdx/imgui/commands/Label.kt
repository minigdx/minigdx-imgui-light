package com.github.minigdx.imgui.commands

import com.github.minigdx.imgui.ImGui
import com.github.minigdx.imgui.atlas.WidgetAtlas
import com.github.minigdx.imgui.internal.Cursor
import com.github.minigdx.imgui.internal.PrimitivesOrder
import com.github.minigdx.imgui.internal.Quad

class Label(
    val text: String
) : Command, Primitive {

    val width = text.sumOf { WidgetAtlas.DEFAULT.text.width(it) }.toFloat()

    override fun addInto(primitivesOrder: PrimitivesOrder) {
        // Add text
        primitivesOrder.add(this)
    }

    override fun advance(cursor: Cursor) {
        cursor.advance(width, 0f)
    }

    override val texture: Any = ImGui.atlas

    override val quads: List<Quad> = computeQuads()

    private fun computeQuads(): List<Quad> {
        var start = 0f
        val quads = mutableListOf<Quad>()
        text.forEach {
            val uvs = WidgetAtlas.DEFAULT.text.letter(it)
            val width = WidgetAtlas.DEFAULT.text.width(it)
            val q = Quad().append(start, 0f, width.toFloat(), Cursor.LINE_HEIGHT, uvs)
            quads.add(q)
            start += width.toFloat()
        }
        return quads
    }
}
