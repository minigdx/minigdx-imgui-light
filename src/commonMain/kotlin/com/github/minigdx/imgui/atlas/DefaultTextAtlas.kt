package com.github.minigdx.imgui.atlas

import com.github.minigdx.imgui.UVCoordinates

class DefaultTextAtlas : WidgetAtlas.TextAtlas {

    private val width = 1 / 256f
    private val height = 1 / 64f

    override fun letter(letter: Char): Pair<UVCoordinates, UVCoordinates> = when (letter) {
        'a' ->
            UVCoordinates(
                0f * width,
                0f * height
            ) to UVCoordinates(
                8f * width,
                10f * height
            )
        else ->
            UVCoordinates(
                1f * width,
                1f * height
            ) to UVCoordinates(
                8f * width,
                10f * height
            )
    }
}
