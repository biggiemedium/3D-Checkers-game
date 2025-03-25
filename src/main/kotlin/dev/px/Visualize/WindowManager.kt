package dev.px.Visualize

import dev.px.Util.Listeners.Impl.IWindow
import org.joml.Matrix4f
import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11
import org.lwjgl.system.MemoryUtil

class WindowManager(
var width: Int,
var height: Int,
private var title: String,
private val vSync: Boolean
) : IWindow {

    var FOV: Double = Math.toRadians(60.0)
    val Z_NEAR: Double = 0.01
    val Z_FAR: Double = 1000.0

    var window: Long = 0
    var resize: Boolean = false
    var projectionMatrix: Matrix4f = Matrix4f()

    override fun init() {
        GLFW.glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.err))

        if (!GLFW.glfwInit()) {
            throw IllegalStateException("Unable to initialize GLFW")
        }

        setup()
    }

    override fun update() {
        GLFW.glfwPollEvents()
    }

    fun refresh() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT or GL11.GL_DEPTH_BUFFER_BIT)
        GLFW.glfwSwapBuffers(window)
    }

    override fun setup() {
        GLFW.glfwDefaultWindowHints()
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, 0) // 1 true 0 false
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, 1) // 1 true 0 false

        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3)
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 3)

        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE) // value: 0x32001
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, 1) // 1 true 0 false

        var maximized:Boolean = false
        if(width == 0 || height == 0) {
            width = 100
            height = 100
            GLFW.glfwWindowHint(GLFW.GLFW_MAXIMIZED, 1)
            maximized = true
        }

        window = GLFW.glfwCreateWindow(width, height, title, MemoryUtil.NULL, MemoryUtil.NULL)
        if(window == MemoryUtil.NULL) {
            throw RuntimeException("Failed to create GLFW Window")
        }

        GLFW.glfwMakeContextCurrent(window)
        if (vSync) {
            GLFW.glfwSwapInterval(1)
        }

        if (!maximized) {
            GLFW.glfwShowWindow(window)
        }

        GL.createCapabilities()
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f)

        GLFW.glfwSetFramebufferSizeCallback(window) { _, newWidth, newHeight ->
            width = newWidth
            height = newHeight
            resize = true
        }

        GL11.glEnable(GL11.GL_DEPTH_TEST)
        projectionMatrix = updateProjectionMatrix()
    }

    fun updateProjectionMatrix(): Matrix4f {
        return Matrix4f().perspective(FOV.toFloat(), width.toFloat() / height, Z_NEAR.toFloat(), Z_FAR.toFloat())
    }


    fun shouldClose(): Boolean {
        return GLFW.glfwWindowShouldClose(window)
    }

    fun isKeyPressed(keyCode: Int): Boolean {
        return GLFW.glfwGetKey(window, keyCode) == GLFW.GLFW_PRESS
    }

    fun close() {
        GLFW.glfwSetWindowShouldClose(window, true)
    }

    fun setTitle(title: String) {
        GLFW.glfwSetWindowTitle(window, title)
    }

    override fun cleanup() {
        GLFW.glfwDestroyWindow(window)
        GLFW.glfwTerminate()
        GLFW.glfwSetErrorCallback(null)?.free()
    }
}
