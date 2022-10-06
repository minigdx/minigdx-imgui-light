package com.github.minigdx.imgui.commands

import com.github.minigdx.imgui.internal.PrimitivesOrder

internal interface Command {

    fun addInto(primitivesOrder: PrimitivesOrder)
}
