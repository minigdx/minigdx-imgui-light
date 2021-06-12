package com.github.minigdx.imgui.atlas

import com.github.minigdx.imgui.internal.Resolution
import com.github.minigdx.imgui.internal.UVCoordinates

class DefaultButtonAtlas(
    private val xOffet: Int = 0,
    private val yOffset: Int = 0,
    override val resolution: Resolution
) : WidgetAtlas.ButtonAtlas {

    override val left: Pair<UVCoordinates, UVCoordinates> = UVCoordinates(
        xOffet + 0,
        yOffset + 33,
        resolution
    ) to UVCoordinates(
        xOffet + 3,
        yOffset + 44,
        resolution
    )
    override val center: Pair<UVCoordinates, UVCoordinates> = UVCoordinates(
        xOffet + 3,
        yOffset + 33,
        resolution
    ) to UVCoordinates(
        xOffet + 5,
        yOffset + 44,
        resolution
    )
    override val right: Pair<UVCoordinates, UVCoordinates> = UVCoordinates(
        xOffet + 5,
        yOffset + 33,
        resolution
    ) to UVCoordinates(
        xOffet + 8,
        yOffset + 44,
        resolution
    )
}
