plugins {
    id("com.github.minigdx.gradle.plugin.developer.mpp") version "1.1.0-alpha0"
}

minigdxDeveloper {
    this.name.set("imGUI Light")
    this.description.set("Light implementation of a Immediate Mode GUI (imGUI)")
    this.projectUrl.set("https://github.com/minigdx/minigdx-imgui-light")
    this.licence {
        name.set("MIT Licence")
        url.set("https://github.com/minigdx/minigdx-imgui-light/blob/master/LICENSE")
    }
    developer {
        name.set("David Wursteisen")
        email.set("david.wursteisen+minigdx@gmail.com")
        url.set("https://github.com/dwursteisen")
    }
}
