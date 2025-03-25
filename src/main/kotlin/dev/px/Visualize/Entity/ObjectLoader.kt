package dev.px.Visualize.Entity

import dev.px.Util.GLUtils
import org.lwjgl.opengl.*

class ObjectLoader {

    var vaos: List<Int> = mutableListOf()
    var vbos: List<Int> = mutableListOf() // fuck kotlin for this

    fun loadModel(verticies: Array<Float>): Model {
        val id: Int = createVAO()
        storeDataInAttribute(0, 3, verticies)
        unBind()
        return Model(id, verticies.size / 3)
    }

    fun createVAO(): Int {
        if (!GL.getCapabilities().OpenGL30) {
            throw IllegalStateException("OpenGL 3.0 context is not active!")
        }
        val id: Int = GL30.glGenVertexArrays()
        this.vaos = vaos + id
        GL30.glBindVertexArray(id)
        return id
    }

    fun storeDataInAttribute(attributeID: Int, vertexsCount: Int, data: Array<Float>) {
        var vbo: Int = GL15.glGenBuffers()
        this.vbos = vbos + vbo
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo)
        GLUtils.storeDataInFloatBuffer(data)
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, GLUtils.storeDataInFloatBuffer(data), GL15.GL_STATIC_DRAW)
        GL20.glVertexAttribPointer(attributeID, vertexsCount, GL11.GL_FLOAT, false, 0, 0)
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0)
    }

    fun unBind() {
        GL30.glBindVertexArray(0)
    }

    fun cleanup() {
        for(v in vbos) {
            GL30.glDeleteVertexArrays(v)
        }
        for(v in vaos) {
            GL30.glDeleteVertexArrays(v)
        }
    }

}