package dev.px.Util

import org.lwjgl.glfw.GLFW
import org.lwjgl.opengl.GL11

class RenderUtil {
companion object {

        public fun resetColor(r: Float, g: Float, b: Float, a: Float) {
            GL11.glClearColor(r, g, b, a)
        }

    }
}