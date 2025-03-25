package dev.px.Visualize

import dev.px.Main
import dev.px.Util.Listeners.Impl.GameLogic
import dev.px.Util.Listeners.Impl.IEngine
import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWErrorCallback

class EngineManager : IEngine {

    var NANO_TIME: Long = 1_000_000_000L
    var FRAMERATE: Float = 0.0F

    var fps: Int = 0
    var frameTime: Float = 1 / FRAMERATE

    var isRunning: Boolean = false

    val window: WindowManager = Main.windowManager
    val errorCallback: GLFWErrorCallback ?= GLFW.glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.err))
    var gameLogic: GameLogic = Main.checkers


    override fun init() {
        window.init()
        gameLogic?.init()
    }

    override fun update() {
        gameLogic?.update()
    }

    override fun input() {
        gameLogic?.input()
    }


    fun start() {
        init()
        if(isRunning) {
            return
        }

        run()
    }

    fun run() {
        isRunning = true
        var frames: Int = 0
        var frameCounter: Long = 0
        var lastTime: Long = System.nanoTime()
        var unprocessedTime: Long = 0L

        if(!GLFW.glfwInit()) {
            throw RuntimeException("Error starting Engine Manager")
        }

        while (isRunning) {
            var render: Boolean = false
            var startTime: Long = System.nanoTime()
            var passedTime: Long = startTime - lastTime
            lastTime = startTime
            unprocessedTime += passedTime / NANO_TIME
            frameCounter += passedTime

            while (unprocessedTime > frameTime) {
                render = true
                unprocessedTime -= frameTime.toLong()

                if(window.shouldClose()) {
                    stop()
                }
                if(frameCounter >= NANO_TIME) {
                    fps = frames
                    window.setTitle("")
                    frames = 0
                    frameCounter = 0
                }
            }

            if(render) {
                update()
                render()
                frames++
            }
        }
        cleanup()
    }

    fun render() {
        gameLogic?.render()
        window.update()
    }

    fun stop() {
        if(!isRunning) { // seems redudant but whatever
            return
        }
        isRunning = false
    }

    fun cleanup() {
        window.cleanup()
        gameLogic?.cleanup()
        errorCallback?.free()
        GLFW.glfwTerminate()
    }

}

