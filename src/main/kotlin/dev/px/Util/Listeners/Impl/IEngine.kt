package dev.px.Util.Listeners.Impl

import dev.px.Util.Listeners.Wrapper

interface IEngine : Wrapper {

    override fun init()

    fun update()

    fun input()


}