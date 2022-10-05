package com.github.minigdx.imgui.commands

import com.github.minigdx.imgui.ImGui
import com.github.minigdx.imgui.internal.Cursor
import com.github.minigdx.imgui.internal.Quad

internal object BreakLine : Primitive {

    override fun advance(cursor: Cursor) {
        cursor.advanceLine()
    }

    override val texture: Any = ImGui.atlas

    override val quads: List<Quad> = emptyList()
}
