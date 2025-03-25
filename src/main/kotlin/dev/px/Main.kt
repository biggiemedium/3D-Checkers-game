package dev.px

import dev.px.Game.Checkers
import dev.px.Util.Listeners.Impl.GameLogic
import dev.px.Visualize.EngineManager
import dev.px.Visualize.WindowManager

class Main {

    companion object {
        val windowManager: WindowManager = WindowManager(1200, 800, "3D Checkers", true)
        val checkers: GameLogic = Checkers()
        val engineManager: EngineManager = EngineManager()

        @JvmStatic fun main(args: Array<String>) {
            checkers.init()
            try {
                engineManager.start()
            } catch (ignored: Exception) {
                ignored.printStackTrace()
            }
        }
    }


}