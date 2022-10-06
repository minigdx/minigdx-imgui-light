package com.github.minigdx.imgui.commands

import com.github.minigdx.imgui.internal.Cursor
import com.github.minigdx.imgui.internal.Quad
import com.github.minigdx.imgui.internal.Resolution
import com.github.minigdx.imgui.internal.UVCoordinates

internal class Picture(
    override val texture: Any,
    val width: Float,
    val height: Float,
) : Primitive {

    override fun advance(cursor: Cursor) {
        cursor.advance(width, height)
    }

    override val quads: List<Quad> = listOf(Quad().append(0f, 0f, width, height, computeUvs()))

    private fun computeUvs(): Pair<UVCoordinates, UVCoordinates> {
        val resolution = Resolution(1f, 1f)
        return UVCoordinates(0f, 0f, resolution) to UVCoordinates(1f, 1f, resolution)
    }
}
