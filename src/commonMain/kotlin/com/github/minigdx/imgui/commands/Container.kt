package com.github.minigdx.imgui.commands

import com.github.minigdx.imgui.internal.Cursor
import com.github.minigdx.imgui.internal.PrimitivesOrder

class Container(
    private val name: String,
    private val builder: Container.() -> Unit,
    private val width: Int = 256
) : Command {

    class MutableBoolean(var value: Boolean = false)

    private lateinit var internalCommands: PrimitivesOrder

    private val mutableBoolean = MutableBoolean()

    override fun addInto(primitivesOrder: PrimitivesOrder) {
        // Header
        primitivesOrder.add(Background(Background.Type.HEADER, width.toFloat(), dark = true))
        primitivesOrder.add(Fill)
        primitivesOrder.add(Label(name))
        primitivesOrder.add(BreakLine)
        // Body
        internalCommands = primitivesOrder
        builder()

        // Footer
        primitivesOrder.add(Background(Background.Type.FOOTER, width.toFloat()))
        primitivesOrder.add(BreakLine)
        // Add another Breakline to have space for the next container.
        primitivesOrder.add(BreakLine)
    }

    fun label(text: String) = addLine {
        Label(text).addInto(internalCommands)
    }

    fun button(text: String, pushed: Boolean = false): Boolean = addLine {
        Button(text, pushed, mutableBoolean).addInto(internalCommands)
        mutableBoolean.value
    }

    fun checkbox(text: String, checked: Boolean = false): Boolean = addLine {
        CheckBox(checked, text, mutableBoolean).addInto(internalCommands)
        internalCommands.add(Fill)
        mutableBoolean.value
    }

    fun texture(texture: Any, width: Int, height: Int) = addLine(height.toFloat() + Cursor.LINE_HEIGHT) {
        internalCommands.add(Picture(texture, width.toFloat(), height.toFloat()))
    }

    private fun <T> addLine(height: Float = Cursor.LINE_HEIGHT, content: Container.() -> T): T {
        internalCommands.add(Background(Background.Type.BODY, width.toFloat(), height))
        internalCommands.add(Fill)
        val result = content()
        internalCommands.add(BreakLine)
        return result
    }
}
