package dev.px.Util.Listeners.Impl

import dev.px.Util.Listeners.Wrapper

interface IWindow : Wrapper {

    override fun init()
    fun setup()
    fun update()
    fun cleanup()

}