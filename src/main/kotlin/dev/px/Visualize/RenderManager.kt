package dev.px.Visualize

import dev.px.Main
import dev.px.Visualize.Entity.Model
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL30

class RenderManager {

    val window: WindowManager = Main.windowManager

    fun init() {

    }

    fun render(mode: Model) {
        clear()
        GL30.glBindVertexArray(mode.id)
        GL20.glEnableVertexAttribArray(0)
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, mode.vertexCount)
        GL20.glDisableVertexAttribArray(0)
        GL30.glBindVertexArray(0)
    }

    fun clear() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT) // kotlin moment
        GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT)
    }

    fun cleanup() {

    }
}