package com.github.minigdx.imgui

interface InputCapture {

    val x: Float
    val y: Float
    val isTouch: Boolean

    fun update()
}
