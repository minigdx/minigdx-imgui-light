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
    }

    val buttonIdle: ButtonAtlas
    val buttonOver: ButtonAtlas
    val buttonActivated: ButtonAtlas

    val label: LabelAtlas

    val text: TextAtlas
}
