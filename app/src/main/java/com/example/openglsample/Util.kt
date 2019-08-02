package com.example.openglsample

import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

object Util {
    /** OpenGL 用のバッファを作成します。 */
    fun makeFloatBuffer(arr: FloatArray): FloatBuffer {
        val bb = ByteBuffer.allocateDirect(arr.size * 4)  // float は 4 byte
        bb.order(ByteOrder.nativeOrder())
        return bb.asFloatBuffer().apply {
            put(arr)
            position(0)
        }
    }
}
