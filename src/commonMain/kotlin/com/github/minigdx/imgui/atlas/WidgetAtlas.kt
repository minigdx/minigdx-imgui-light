package com.github.minigdx.imgui.atlas

import com.github.minigdx.imgui.internal.Resolution
import com.github.minigdx.imgui.internal.UVCoordinates

interface WidgetAtlas {

    val resolution: Resolution

    interface ButtonAtlas {

        val resolution: Resolution
        val left: Pair<UVCoordinates, UVCoordinates>
        val center: Pair<UVCoordinates, UVCoordinates>
        val right: Pair<UVCoordinates, UVCoordinates>
    }

    interface LabelAtlas {

        val resolution: Resolution
        val left: Pair<UVCoordinates, UVCoordinates>
        val center: Pair<UVCoordinates, UVCoordinates>
        val right: Pair<UVCoordinates, UVCoordinates>
    }

    interface TextAtlas {

        val resolution: Resolution
        fun letter(letter: Char): Pair<UVCoordinates, UVCoordinates>

        fun width(letter: Char): Int
    }

    val buttonIdle: ButtonAtlas
    val buttonOver: ButtonAtlas
    val buttonActivated: ButtonAtlas

    val containerTopDark: ButtonAtlas
    val containerBodyDark: ButtonAtlas
    val containerBottomDark: ButtonAtlas

    val containerTopClear: ButtonAtlas
    val containerBodyClear: ButtonAtlas
    val containerBottomClear: ButtonAtlas

    val checkboxUnchecked: Pair<UVCoordinates, UVCoordinates>
    val checkboxChecked: Pair<UVCoordinates, UVCoordinates>

    val label: LabelAtlas

    val text: TextAtlas

    companion object {
        val DEFAULT = DefaultWidgetAtlas()
    }
}
