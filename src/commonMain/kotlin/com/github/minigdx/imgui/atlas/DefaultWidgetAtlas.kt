package com.github.minigdx.imgui.atlas

import com.github.minigdx.imgui.internal.Resolution

class DefaultWidgetAtlas : WidgetAtlas {

    override val resolution: Resolution = Resolution(256, 64)

    override val buttonIdle: WidgetAtlas.ButtonAtlas = DefaultButtonAtlas(resolution = resolution)
    override val buttonOver: WidgetAtlas.ButtonAtlas = DefaultButtonAtlas(xOffet = 8, resolution = resolution)
    override val buttonActivated: WidgetAtlas.ButtonAtlas = DefaultButtonAtlas(resolution = resolution)
    override val label: WidgetAtlas.LabelAtlas
        get() = TODO("Not yet implemented")
    override val text: WidgetAtlas.TextAtlas = DefaultTextAtlas(resolution)
}
