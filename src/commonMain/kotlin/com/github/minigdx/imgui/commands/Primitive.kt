package com.github.minigdx.imgui.commands

import com.github.minigdx.imgui.internal.Cursor
import com.github.minigdx.imgui.internal.Quad

interface Primitive {

    fun advance(cursor: Cursor)

    val texture: Any

    val quads: List<Quad>
}
