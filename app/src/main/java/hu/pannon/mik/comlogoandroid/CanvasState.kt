package hu.pannon.mik.comlogoandroid

import android.graphics.Color

class CanvasState {
    var isPenDown: Boolean = true
    var penColor: Int = Color.BLACK
    var penStrokeWidth: Float = 5F
    var turtle: Turtle = Turtle(0.0, 0.0)
}