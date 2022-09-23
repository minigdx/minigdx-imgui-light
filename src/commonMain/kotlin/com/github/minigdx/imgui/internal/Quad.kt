package com.github.minigdx.imgui.internal

import com.github.minigdx.imgui.Batch

/**
 * Add a Quad to the vertices.
 */
class Quad(
    val vertices: ArrayList<Float> = arrayListOf(),
    val verticesOrder: ArrayList<Int> = arrayListOf(),
    val uvs: ArrayList<Float> = arrayListOf(),
) {

    constructor(batch: Batch<*>) : this(batch.vertices, batch.verticesOrder, batch.uvs)

    fun append(x: Float, y: Float, with: Float, height: Float, uv: Pair<UVCoordinates, UVCoordinates>): Quad {
        val vertexIndex = vertices.size / 3
        // 0
        addVertex(x, y, 0f)
        // 1
        addVertex((x + with), y, 0f)
        // 2
        addVertex((x + with), (y + height), 0f)
        // 3
        addVertex(x, (y + height), 0f)

        // 0
        uvs.add(uv.first.x)
        uvs.add(uv.first.y)

        // 1
        uvs.add(uv.second.x)
        uvs.add(uv.first.y)

        // 2
        uvs.add(uv.second.x)
        uvs.add(uv.second.y)

        // 3
        uvs.add(uv.first.x)
        uvs.add(uv.second.y)

        // triangle up
        verticesOrder.addAll(
            listOf(vertexIndex + 0, vertexIndex + 1, vertexIndex + 2)
        )

        // triangle down
        verticesOrder.addAll(
            listOf(vertexIndex + 2, vertexIndex + 3, vertexIndex + 0)
        )

        return this
    }

    private fun addVertex(x: Float, y: Float, z: Float) {
        vertices.add(x)
        vertices.add(y)
        vertices.add(z)
    }
}
