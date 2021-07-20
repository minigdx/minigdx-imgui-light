package com.github.minigdx.imgui

interface ImGUIRenderer<TEXTURE> {

    fun render(texture: TEXTURE, vertices: FloatArray, uv: FloatArray, verticesOrder: IntArray)
}
