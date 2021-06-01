package com.github.minigdx.imgui.internal

data class Cursor(val way: CursorWay, var x: Float, var y: Float) {

    enum class CursorWay {
        HORIZONTAL,
        VERTICAL
    }

    fun advance(with: Float, height: Float) {
        when (way) {
            CursorWay.HORIZONTAL -> x += with
            CursorWay.VERTICAL -> y += height
        }
    }
}
