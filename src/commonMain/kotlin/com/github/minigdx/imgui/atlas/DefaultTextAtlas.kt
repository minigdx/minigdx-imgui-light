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
        create('A', 1, 1, 7, 10),
        create('B', 8, 1, 7, 10),
        create('C', 16, 1, 6, 10),
        create('D', 24, 1, 7, 10),
        create('E', 32, 1, 6, 10),
        create('F', 40, 1, 6, 10),
        create('G', 48, 1, 8, 10),
        create('H', 56, 1, 6, 10),
        create('I', 64, 1, 6, 10),
        create('J', 72, 1, 6, 10),
        create('K', 80, 1, 8, 10),
        create('L', 88, 1, 7, 10),
        create('M', 96, 1, 9, 10),
        create('N', 104, 1, 8, 10),
        create('O', 112, 1, 6, 10),
        create('P', 120, 1, 7, 10),
        create('Q', 128, 1, 7, 10),
        create('R', 136, 1, 6, 10),
        create('S', 144, 1, 6, 10),
        create('T', 152, 1, 7, 10),
        create('U', 160, 1, 6, 10),
        create('V', 168, 1, 7, 10),
        create('W', 176, 1, 8, 10),
        create('X', 184, 1, 7, 10),
        create('Y', 192, 1, 8, 10),
        create('Z', 200, 1, 8, 10),
        create('0', 1, 11, 6, 10),
        create('1', 1, 11, 6, 10),
        create('2', 1, 11, 6, 10),
        create('3', 1, 11, 6, 10),
        create('4', 1, 11, 6, 10),
        create('5', 1, 11, 6, 10),
        create('6', 1, 11, 6, 10),
        create('7', 1, 11, 6, 10),
        create('8', 1, 11, 6, 10),
        create('9', 1, 11, 6, 10),
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
