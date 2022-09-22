package com.github.minigdx.imgui

import com.github.minigdx.imgui.commands.Container
import com.github.minigdx.imgui.commands.Primitive
import com.github.minigdx.imgui.internal.Cursor
import com.github.minigdx.imgui.internal.PrimitivesOrder

object ImGui {

    private var isDrawing = false

    internal lateinit var atlas: Any
    internal lateinit var io: InputCapture

    private val commands = mutableListOf<Primitive>()

    private val cursor = Cursor(Cursor.CursorWay.HORIZONTAL)

    private val primitivesOrder = PrimitivesOrder(commands, cursor)

    // List of all batch to be displayed
    val batch: List<Batch<Any>> = primitivesOrder.batches

    fun setup(atlas: Any, io: InputCapture) {
        this.atlas = atlas
        this.io = io
    }

    fun container(name: String, builder: Container.() -> Unit = { }) {
        val command = Container(name, builder)
        command.addInto(primitivesOrder)
    }

    fun beginFrame() {
        if (isDrawing) throw IllegalStateException(
            "The application is calling twice beginFrame() without " +
                "calling endFrame() in between."
        )

        if (primitivesOrder.batches.isEmpty()) {
            isDrawing = true
            return
        }

        isDrawing = true
    }

    fun endFrame() {
        if (!isDrawing) throw IllegalStateException(
            "The application is calling endFrame() without " +
                "calling beginFrame() first."
        )
        primitivesOrder.clear()
        isDrawing = false
    }

    internal fun isOver(x: Float, y: Float, width: Float, height: Float): Boolean {
        return (io.x in x..(x + width) && io.y in y..(y + height))
    }

    internal fun isPushed(): Boolean {
        return io.isTouch
    }

    internal fun isPushedOver(x: Float, y: Float, width: Float, height: Float): Boolean {
        return isPushed() && isOver(x, y, width, height)
    }
}
