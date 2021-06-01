package com.github.minigdx.imgui.atlas

import com.github.minigdx.imgui.UVCoordinates

class DefaultButtonAtlas(private val xOffet: Float = 0f, private val yOffset: Float = 0f) : WidgetAtlas.ButtonAtlas {

    private val width = 1 / 256f
    private val height = 1 / 64f

    override val left: Pair<UVCoordinates, UVCoordinates> = UVCoordinates(
        xOffet + 0f * width,
        yOffset + 36f * height
    ) to UVCoordinates(
        xOffet + 6f * width,
        yOffset + 48f * height
    )
    override val center: Pair<UVCoordinates, UVCoordinates> = UVCoordinates(
        xOffet + 6f * width,
        yOffset + 36f * height
    ) to UVCoordinates(
        xOffet + 8f * width,
        yOffset + 48f * height
    )
    override val right: Pair<UVCoordinates, UVCoordinates> = UVCoordinates(
        xOffet + 6f * width,
        yOffset + 36f * height
    ) to UVCoordinates(
        xOffet + 12f * width,
        yOffset + 48f * height
    )
}
