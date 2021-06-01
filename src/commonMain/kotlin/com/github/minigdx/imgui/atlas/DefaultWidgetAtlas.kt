package com.github.minigdx.imgui.atlas

import com.github.minigdx.imgui.internal.Resolution

class DefaultWidgetAtlas : WidgetAtlas {

    override val resolution: Resolution = Resolution(256, 64)

    override val buttonIdle: WidgetAtlas.ButtonAtlas = DefaultButtonAtlas()
    override val buttonOver: WidgetAtlas.ButtonAtlas = DefaultButtonAtlas(xOffet = 12 / resolution.width)
    override val buttonActivated: WidgetAtlas.ButtonAtlas = DefaultButtonAtlas()
    override val label: WidgetAtlas.LabelAtlas
        get() = TODO("Not yet implemented")
    override val text: WidgetAtlas.TextAtlas = DefaultTextAtlas()
}
