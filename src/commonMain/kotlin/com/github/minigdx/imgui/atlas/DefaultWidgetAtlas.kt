package com.github.minigdx.imgui.atlas

import com.github.minigdx.imgui.internal.Resolution
import com.github.minigdx.imgui.internal.UVCoordinates

class DefaultWidgetAtlas : WidgetAtlas {

    override val resolution: Resolution = Resolution(256, 64)

    override val buttonIdle: WidgetAtlas.ButtonAtlas = DefaultButtonAtlas(resolution = resolution)
    override val buttonOver: WidgetAtlas.ButtonAtlas = DefaultButtonAtlas(xOffet = 8, resolution = resolution)
    override val buttonActivated: WidgetAtlas.ButtonAtlas = DefaultButtonAtlas(resolution = resolution)

    override val containerTopDark: WidgetAtlas.ButtonAtlas = DefaultButtonAtlas(xOffet = 16, resolution = resolution)
    override val containerBodyDark: WidgetAtlas.ButtonAtlas = DefaultButtonAtlas(xOffet = 32, resolution = resolution)
    override val containerBottomDark: WidgetAtlas.ButtonAtlas = DefaultButtonAtlas(xOffet = 48, resolution = resolution)

    override val containerTopClear: WidgetAtlas.ButtonAtlas = DefaultButtonAtlas(xOffet = 24, resolution = resolution)
    override val containerBodyClear: WidgetAtlas.ButtonAtlas = DefaultButtonAtlas(xOffet = 40, resolution = resolution)
    override val containerBottomClear: WidgetAtlas.ButtonAtlas = DefaultButtonAtlas(
        xOffet = 56,
        resolution = resolution
    )
    override val label: WidgetAtlas.LabelAtlas
        get() = TODO("Not yet implemented")
    override val text: WidgetAtlas.TextAtlas = DefaultTextAtlas(resolution)

    override val checkboxUnchecked: Pair<UVCoordinates, UVCoordinates>  = UVCoordinates(64, 33, resolution) to UVCoordinates(72, 44, resolution)
    override val checkboxChecked: Pair<UVCoordinates, UVCoordinates>  = UVCoordinates(72, 33, resolution) to UVCoordinates(80, 44, resolution)
}
