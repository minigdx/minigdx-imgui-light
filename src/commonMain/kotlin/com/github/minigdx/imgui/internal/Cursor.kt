package com.github.minigdx.imgui.internal

import com.github.minigdx.imgui.atlas.WidgetAtlas
import com.github.minigdx.imgui.commands.Primitive

data class Cursor(val way: CursorWay, var x: Float = FILL_WIDTH, var y: Float = FILL_WIDTH) {

    enum class CursorWay {
        HORIZONTAL,
        VERTICAL
    }
    fun reset(x: Float = FILL_WIDTH, y: Float = FILL_WIDTH) {
        this.x = x
        this.y = y
    }

    fun advance(with: Float, height: Float) {
        when (way) {
            CursorWay.HORIZONTAL -> x += with
            CursorWay.VERTICAL -> y += height
        }
    }

    fun advance(command: Primitive) {
        command.advance(this)
    }

    fun advanceText(content: String) {
        x += content.sumOf { WidgetAtlas.DEFAULT.text.width(it) }
    }

    fun advanceLine() {
        y += LINE_HEIGHT
        x = FILL_WIDTH
    }

    fun advanceFill() {
        x += FILL_WIDTH
    }

    companion object {
        const val LINE_HEIGHT = 11f
        const val FILL_WIDTH = 2f
    }
}
