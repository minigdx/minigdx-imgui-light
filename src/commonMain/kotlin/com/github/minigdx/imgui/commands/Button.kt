package com.github.minigdx.imgui.commands

import com.github.minigdx.imgui.ImGui
import com.github.minigdx.imgui.internal.Cursor
import com.github.minigdx.imgui.internal.PrimitivesOrder

class Button(
    private val text: String,
    private val isPushed: Boolean,
    private val hasBeenPushed: Container.MutableBoolean
) : Command {

    override fun addInto(primitivesOrder: PrimitivesOrder) {

        val label = Label(text)
        val width = label.width + Cursor.FILL_WIDTH * 2f
        val ioOver = ImGui.isOver(primitivesOrder.x, primitivesOrder.y, width, Cursor.LINE_HEIGHT)
        val ioPushed = ImGui.isPushed()

        val type = if (ioOver && ioPushed) {
            hasBeenPushed.value = true
            Background.Type.BUTTON_PUSHED
        } else if (ioOver && !ioPushed) {
            hasBeenPushed.value = false
            Background.Type.BUTTON_OVER
        } else if (isPushed) {
            hasBeenPushed.value = false
            Background.Type.BUTTON_PUSHED
        } else {
            hasBeenPushed.value = false
            Background.Type.BUTTON
        }
        primitivesOrder.add(Background(type, width))
        primitivesOrder.add(Fill)
        label.addInto(primitivesOrder)
        primitivesOrder.add(Fill)
    }
}
