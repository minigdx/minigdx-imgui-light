package com.github.minigdx.imgui.internal

data class Resolution(val width: Float, val height: Float) {
    constructor(width: Int, height: Int) : this(width.toFloat(), height.toFloat())
}
