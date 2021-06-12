package com.github.minigdx.imgui.internal

/**
 * UV Coordinates in percent (0.0..1.0)
 */
data class UVCoordinates(val x: Float, val y: Float, private val resolution: Resolution) {

    /**
     * UV Coordinates in PIXEL
     */
    constructor(x: Int, y: Int, resolution: Resolution) : this(
        x.toFloat() / resolution.width,
        y.toFloat() / resolution.height,
        resolution
    )
    fun convert(other: Resolution): UVCoordinates {
        return UVCoordinates(
            x * resolution.width / other.width,
            y * resolution.height / other.height,
            other
        )
    }
}
