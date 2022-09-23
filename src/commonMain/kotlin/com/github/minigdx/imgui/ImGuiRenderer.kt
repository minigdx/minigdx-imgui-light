package com.github.minigdx.imgui

interface ImGuiRenderer<TEXTURE> {

    fun render(texture: TEXTURE, vertices: FloatArray, uv: FloatArray, verticesOrder: IntArray)
}
