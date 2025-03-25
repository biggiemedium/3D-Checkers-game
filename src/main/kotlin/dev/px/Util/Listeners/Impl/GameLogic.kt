package dev.px.Util.Listeners.Impl


interface GameLogic : dev.px.Util.Listeners.Wrapper {

    override fun init()

    fun input()

    fun render()

    fun update()

    fun cleanup()

}