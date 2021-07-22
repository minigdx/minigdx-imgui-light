package com.github.minigdx.imgui.atlas

import com.github.minigdx.imgui.internal.Resolution
import com.github.minigdx.imgui.internal.UVCoordinates

class DefaultTextAtlas(override val resolution: Resolution) : WidgetAtlas.TextAtlas {

    private val cache = mapOf(
        create('a', 0, 11, 6, 11),
        create('b', 7, 11, 5, 11),
        create('c', 13, 11, 7, 11),
        create('d', 21, 11, 6, 11),
        create('e', 28, 11, 6, 11),
        create('f', 35, 11, 6, 11),
        create('g', 42, 11, 6, 11),
        create('h', 49, 11, 6, 11),
        create('i', 57, 11, 4, 11),
        create('j', 63, 11, 6, 11),
        create('k', 69, 11, 6, 11),
        create('l', 77, 11, 6, 11),
        create('m', 82, 11, 6, 11),
        create('n', 90, 11, 6, 11),
        create('o', 97, 11, 6, 11),
        create('p', 104, 11, 6, 11),
        create('q', 111, 11, 6, 11),
        create('r', 118, 11, 6, 11),
        create('s', 125, 11, 6, 11),
        create('t', 133, 11, 6, 11),
        create('u', 139, 11, 6, 11),
        create('v', 147, 11, 6, 11),
        create('w', 152, 11, 6, 11),
        create('x', 160, 11, 6, 11),
        create('y', 167, 11, 6, 11),
        create('z', 174, 11, 6, 11),
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
        create('0', 208, 0, 6, 10),
        create('1', 216, 0, 6, 10),
        create('2', 224, 0, 6, 10),
        create('3', 232, 0, 6, 10),
        create('4', 240, 0, 6, 10),
        create('5', 208, 11, 6, 10),
        create('6', 216, 11, 6, 10),
        create('7', 225, 11, 6, 10),
        create('8', 232, 11, 6, 10),
        create('9', 240, 11, 6, 10),
        create('{', 0, 22, 6, 10),
        create('}', 6, 22, 6, 10),
        create('[', 14, 22, 6, 10),
        create(']', 21, 22, 6, 10),
        create('(', 28, 22, 6, 10),
        create(')', 35, 22, 6, 10),
        create('<', 40, 22, 6, 10),
        create('>', 48, 22, 6, 10),
        create('$', 55, 22, 6, 10),
        create('*', 62, 22, 6, 10),
        create('-', 69, 22, 6, 10),
        create('+', 76, 22, 6, 10),
        create('=', 83, 22, 6, 10),
        create('/', 90, 22, 6, 10),
        create('#', 96, 22, 6, 10),
        create('_', 104, 22, 6, 10),
        create('%', 111, 22, 6, 10),
        create('@', 125, 22, 6, 10),
        create('\\', 133, 22, 6, 10),
        create('&', 139, 22, 6, 10),
        create('|', 147, 22, 6, 10),
        create('~', 152, 22, 6, 10),
        create('?', 161, 22, 6, 10),
        create('\'', 169, 22, 6, 10),
        create('"', 175, 22, 6, 10),
        create('!', 189, 22, 6, 10),
        create(',', 194, 22, 6, 10),
        create('.', 201, 22, 6, 10),
        create(';', 208, 22, 6, 10),
        create(':', 216, 22, 6, 10),

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
