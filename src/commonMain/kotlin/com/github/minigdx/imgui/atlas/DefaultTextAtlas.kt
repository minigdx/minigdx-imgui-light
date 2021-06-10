package com.github.minigdx.imgui.atlas

import com.github.minigdx.imgui.internal.Resolution
import com.github.minigdx.imgui.internal.UVCoordinates

class DefaultTextAtlas(override val resolution: Resolution) : WidgetAtlas.TextAtlas {

    private val cache = mapOf(
        create('a', 1, 11, 6, 11),
        create('b', 7, 11, 5, 11),
        create('c', 1, 11, 6, 11),
        create('d', 1, 11, 6, 11),
        create('e', 1, 11, 6, 11),
        create('f', 1, 11, 6, 11),
        create('g', 1, 11, 6, 11),
        create('h', 1, 11, 6, 11),
        create('i', 1, 11, 6, 11),
        create('j', 1, 11, 6, 11),
        create('k', 1, 11, 6, 11),
        create('l', 1, 11, 6, 11),
        create('m', 1, 11, 6, 11),
        create('n', 1, 11, 6, 11),
        create('o', 1, 11, 6, 11),
        create('p', 1, 11, 6, 11),
        create('q', 1, 11, 6, 11),
        create('r', 1, 11, 6, 11),
        create('s', 1, 11, 6, 11),
        create('t', 1, 11, 6, 11),
        create('u', 1, 11, 6, 11),
        create('v', 1, 11, 6, 11),
        create('w', 1, 11, 6, 11),
        create('x', 1, 11, 6, 11),
        create('y', 1, 11, 6, 11),
        create('z', 1, 11, 6, 11),
        create('A', 1, 1, 7, 11),
        create('B', 8, 1, 7, 11),
        create('C', 16, 1, 6, 11),
        create('D', 23, 1, 7, 11),
        create('E', 30, 1, 6, 11),
        create('F', 36, 1, 6, 11),
        create('G', 43, 1, 7, 11),
        create('H', 51, 1, 6, 11),
        create('I', 58, 1, 6, 11),
        create('J', 65, 1, 6, 11),
        create('K', 70, 1, 8, 11),
        create('L', 78, 1, 7, 11),
        create('M', 84, 1, 9, 11),
        create('N', 92, 1, 7, 11),
        create('O', 100, 1, 6, 11),
        create('P', 107, 1, 7, 11),
        create('Q', 114, 1, 7, 11),
        create('R', 121, 1, 6, 11),
        create('S', 128, 1, 6, 11),
        create('T', 135, 1, 7, 11),
        create('U', 142, 1, 6, 11),
        create('V', 148, 1, 7, 11),
        create('W', 155, 1, 8, 11),
        create('X', 163, 1, 7, 11),
        create('Y', 170, 1, 7, 11),
        create('Z', 177, 1, 8, 11),
        create('0', 1, 11, 6, 11),
        create('1', 1, 11, 6, 11),
        create('2', 1, 11, 6, 11),
        create('3', 1, 11, 6, 11),
        create('4', 1, 11, 6, 11),
        create('5', 1, 11, 6, 11),
        create('6', 1, 11, 6, 11),
        create('7', 1, 11, 6, 11),
        create('8', 1, 11, 6, 11),
        create('9', 1, 11, 6, 11),
    )

    private val default = create(' ', 160, 22, 8, 10)

    private fun create(
        char: Char,
        x: Int,
        y: Int,
        with: Int,
        height: Int
    ): Pair<Char, Pair<UVCoordinates, UVCoordinates>> {
        val endX = x + with
        val endY = y + height

        return char to (UVCoordinates(x, y, resolution) to UVCoordinates(endX, endY, resolution))
    }

    override fun letter(letter: Char): Pair<UVCoordinates, UVCoordinates> {
        return cache[letter] ?: default.second
    }
}
