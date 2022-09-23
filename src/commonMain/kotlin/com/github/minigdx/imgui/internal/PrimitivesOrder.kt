package com.github.minigdx.imgui.internal

import com.github.minigdx.imgui.Batch
import com.github.minigdx.imgui.ImGui
import com.github.minigdx.imgui.commands.Primitive

class PrimitivesOrder(private val primitives: MutableList<Primitive>, private val cursor: Cursor) {

    val x: Float
        get() = cursor.x

    val y: Float
        get() = cursor.y

    val batches: MutableList<Batch<Any>> = mutableListOf()

    private val internalBatch: MutableMap<Any, Batch<Any>> = mutableMapOf()

    fun add(primitive: Primitive) {
        val currentBatch = getBatch(primitive)
        currentBatch.append(cursor, primitive.quads)
        primitives.add(primitive)
        cursor.advance(primitive)
    }

    private fun getBatch(primitive: Primitive): Batch<Any> {
        val batch = internalBatch[primitive.texture]
        return batch ?: Batch(texture = ImGui.atlas).also { newBatch ->
            internalBatch[primitive.texture] = newBatch
            batches.add(newBatch)
        }
    }

    fun addAll(primitives: Iterable<Primitive>) {
        this.primitives.addAll(primitives)
        primitives.forEach { primitive ->
            val currentBatch = getBatch(primitive)
            currentBatch.append(cursor, primitive.quads)
            cursor.advance(primitive)
        }
    }

    fun clear() {
        primitives.clear()
        internalBatch.clear()
        batches.clear()
        cursor.reset()
    }
}
