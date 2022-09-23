package com.github.minigdx.imgui

import com.github.minigdx.imgui.internal.Cursor
import com.github.minigdx.imgui.internal.Quad

class Batch<TEXTURE>(
    val vertices: ArrayList<Float> = arrayListOf(),
    val uvs: ArrayList<Float> = arrayListOf(),
    val verticesOrder: ArrayList<Int> = arrayListOf(),
    val texture: TEXTURE
) {

    fun append(cursor: Cursor, quads: List<Quad>) {
        quads.forEach { quad ->
            // Add vertices quads in the batch context
            val verticesOrderIndex = vertices.size / 3

            ((0 until quad.vertices.size) step STEPS_VERTICES).forEach { index ->
                val x = quad.vertices[index + 0] + cursor.x
                val y = quad.vertices[index + 1] + cursor.y
                val z = quad.vertices[index + 2] + 0f

                vertices.addAll(listOf(x, y, z))
            }

            verticesOrder.addAll(quad.verticesOrder.map { order -> verticesOrderIndex + order })

            // Add UVs
            uvs.addAll(quad.uvs)
        }
    }

    companion object {

        private const val STEPS_VERTICES = 3
    }
}
