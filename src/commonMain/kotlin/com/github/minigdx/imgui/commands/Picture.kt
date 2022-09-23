package com.github.minigdx.imgui.commands

import com.github.minigdx.imgui.internal.Cursor
import com.github.minigdx.imgui.internal.PrimitivesOrder
import com.github.minigdx.imgui.internal.Quad
import com.github.minigdx.imgui.internal.TextureDescription

class Picture<TEXTURE>(
    override val texture: TextureDescription<TEXTURE>
) : Command, Primitive {

    override fun addInto(primitivesOrder: PrimitivesOrder) {
        TODO("Not yet implemented")
    }

    override fun advance(cursor: Cursor) {
        TODO("Not yet implemented")
    }

    override val quads: List<Quad>
        get() = TODO("Not yet implemented")
}
