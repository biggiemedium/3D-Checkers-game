package dev.px.Game

import dev.px.Main
import dev.px.Util.Listeners.Impl.GameLogic
import dev.px.Util.RenderUtil
import dev.px.Visualize.Entity.Model
import dev.px.Visualize.Entity.ObjectLoader
import dev.px.Visualize.RenderManager
import dev.px.Visualize.WindowManager
import org.lwjgl.glfw.GLFW
import org.lwjgl.opengl.GL11

class Checkers : GameLogic {

    var direction: Int = 0
    var color: Float = 0.0F

    val window: WindowManager = Main.windowManager
    val renderManager: RenderManager = RenderManager()
    val loader: ObjectLoader = ObjectLoader()
    val model: Model? = null

    override fun init() {
        renderManager.init()
        val vertices: Array<Float> = arrayOf(
            -0.5f, 0.5f, 0f,
            -0.5f, -0.5f, 0f,
            0.5f, -0.5f, 0f,
            0.5f, -0.5f, 0f,
            0.5f, 0.5f, 0f,
            -0.5f, 0.5f, 0f
        )
        loader.loadModel(vertices)
    }

    override fun input() {
        if(window.isKeyPressed(GLFW.GLFW_KEY_UP)) {
            direction = 1
        }
        if(window.isKeyPressed(GLFW.GLFW_KEY_DOWN)) {
            direction = 0
        }
    }

    override fun render() {
        if(window.resize) {
            GL11.glViewport(0, 0, window.width, window.height)
            window.resize = true
        }

        RenderUtil.resetColor(color, color, color, 0.0F)
        renderManager.render(model!!)
    }

    override fun update() {
        color += direction * 0.01F
        if(color > 1) {
            color = 1.0F
        } else if(color <= 0) {
            color = 0.0F
        }
    }

    override fun cleanup() {
        renderManager.cleanup()
        loader.cleanup()
    }

}