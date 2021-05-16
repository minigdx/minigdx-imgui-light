package com.github.minigdx.imgui

@ImGUI
interface WidgetBuilder {

    var x: Int
    var y: Int

    fun row(widgetBuilder: WidgetBuilder.() -> Unit)

    fun button(buttonBuilder: ButtonBuilder.() -> Unit)

    fun label(labelBuilder: LabelBuilder.() -> Unit)

    fun slider(sliderBuilder: SliderBuilder.() -> Unit)
}

interface Widget {
    var x: Int
    var y: Int
    var width: Int
    var height: Int
}

@ImGUI
interface ButtonBuilder : Widget {
    var label: String
}

@ImGUI
interface LabelBuilder : Widget
@ImGUI
interface SliderBuilder : Widget

class RowWidgetBuilderImpl(x: Int, y: Int) : WidgetBuilder {
    override var x: Int = x
    override var y: Int = y

    override fun row(widgetBuilder: WidgetBuilder.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun button(buttonBuilder: ButtonBuilder.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun label(labelBuilder: LabelBuilder.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun slider(sliderBuilder: SliderBuilder.() -> Unit) {
        TODO("Not yet implemented")
    }

}

class ButtonBuilderImpl : ButtonBuilder {
    override var x: Int = 0
    override var y: Int = 0
    override var width: Int = 0
    override var height: Int = 0
    override var label: String = ""
}

@DslMarker
annotation class ImGUI


class WidgetBuilderImpl : WidgetBuilder {
    override var x: Int = 0
    override var y: Int = 0

    var currentVertice = 0
    var vertices = arrayListOf<Float>()

    override fun row(widgetBuilder: WidgetBuilder.() -> Unit) {
        val builder = RowWidgetBuilderImpl(x, y)
        widgetBuilder(builder)
        this.x = builder.x
        this.y = builder.y
    }

    override fun button(buttonBuilder: ButtonBuilder.() -> Unit) {
        val builder = ButtonBuilderImpl()
        buttonBuilder(builder)
        this.x += builder.width
        this.y += builder.height
    }

    override fun label(labelBuilder: LabelBuilder.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun slider(sliderBuilder: SliderBuilder.() -> Unit) {
        TODO("Not yet implemented")
    }
}

// taille des widgets en dur.
fun gui(builder: WidgetBuilder.() -> Unit) {
    val widgetBuilder = WidgetBuilderImpl()
    builder(widgetBuilder)

    // donner les vertex +
    // vertex order
    // uv de la texture.
}

fun main() {
    gui {
        row {
            button {
                label = "something"
            }

            slider {


            }
        }

    }
}