package com.github.minigdx.imgui

import kotlin.test.Test
import kotlin.test.assertEquals

class ImGuiTest {

    @Test
    fun itCreatesABatch() {
        ImGui.setup(Unit, object : InputCapture {
            override val x: Float = 0f
            override val y: Float = 0f
            override val isTouch: Boolean = false
        })

        ImGui.container("test")
        ImGui.beginFrame()
        val batch = ImGui.batch.first()

        val (x, y, z) = batch.vertices
        assertEquals(2f, x)
        assertEquals(2f, y)
        assertEquals(0f, z)
    }
}
