package dev.px.Util

import org.lwjgl.system.MemoryUtil
import java.nio.FloatBuffer

class GLUtils {

    companion object {


        fun storeDataInFloatBuffer(data: Array<Float>): FloatBuffer {
            var buffer: FloatBuffer = MemoryUtil.memAllocFloat(data.size)
            buffer.put(data.toFloatArray()).flip()
            return buffer
        }




    }
}