package com.github.minigdx.imgui.commands

import com.github.minigdx.imgui.internal.PrimitivesOrder

interface Command {

    fun addInto(primitivesOrder: PrimitivesOrder)
}
