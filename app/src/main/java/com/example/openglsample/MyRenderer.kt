package com.example.openglsample

import android.opengl.GLSurfaceView
import android.opengl.GLU
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class MyRenderer : GLSurfaceView.Renderer {
    /** Called when the surface is created or recreated. */
    override fun onSurfaceCreated(gl: GL10, config: EGLConfig) {
        // 背景色を設定（暗い青）
        gl.glClearColor(0.0f, 0.0f, 0.5f, 1.0f)  // RGBA
    }

    /** Called when the surface changed size. */
    override fun onSurfaceChanged(gl: GL10, width: Int, height: Int) {
        // ビューポート（描画領域）の設定
        gl.glViewport(0, 0, width, height)

        // 視体積の設定 (left, right, bottom, top, near, far)
        val ratio = width.toFloat() / height
        gl.glMatrixMode(GL10.GL_PROJECTION)
        gl.glLoadIdentity()
        gl.glFrustumf(-ratio, ratio, -1f, 1f, 2f, 6f)

        // 視点と視線方向の設定
        GLU.gluLookAt(gl,
            0f, 0f, 4f,  // カメラの位置（視点）
            0f, 0f, 0f,  // カメラの向き（注視点）
            0f, 1f, 0f   // カメラ姿勢（上方向を表すベクトル）
        )
    }

    /** Called to draw the current frame. */
    override fun onDrawFrame(gl: GL10) {
        // 画面のクリア
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT)

        // 3つの頂点の座標をセット
        val vertices = floatArrayOf(
            -1f, -0.5f, 0f,  // (x, y, z)
             1f, -0.5f, 0f,  // (x, y, z)
             0f,    1f, 0f   // (x, y, z)
        )
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, Util.makeFloatBuffer(vertices))
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY)

        // 3つの頂点の色をセット（RGBA の繰り返し）
        val colors = floatArrayOf(
            1.0f, 1.0f, 0.0f, 1.0f,  // #FFFF00 - 黄色
            0.0f, 1.0f, 1.0f, 1.0f,  // #00FFFF - シアン
            1.0f, 0.0f, 1.0f, 1.0f   // #FF00FF - マゼンタ
        )
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, Util.makeFloatBuffer(colors))
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY)

        // 単色でよければ下記のような2行で OK
        // gl.glColor4f(1.0f, 1.0f, 0f, 0f)  // 黄色
        // gl.disableClientState(GL10.GL_COLOR_ARRAY)

        // 描画（三角形の組み合わせで描画）
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertices.size / 3)
    }
}
