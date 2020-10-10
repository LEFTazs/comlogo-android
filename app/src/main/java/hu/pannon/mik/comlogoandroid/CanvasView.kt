package hu.pannon.mik.comlogoandroid

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.ImageView
import androidx.constraintlayout.solver.widgets.Rectangle
import androidx.core.graphics.drawable.toBitmap


class CanvasView: ImageView {

    constructor(context: Context?): super(context)

    constructor(context: Context?, attrs: AttributeSet?): super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int): super(context, attrs, defStyle)

    private var canvas: Canvas? = null

    private var commandInterpreter = CommandInterpreter()

    private var turtleIcon: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.turtle)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        this.canvas = canvas

        drawLines()
        drawTurtle()
    }

    private fun drawLines() {
        for (line: Line in commandInterpreter.canvasState.lines) {
            val paint = Paint()
            paint.strokeWidth = line.strokeWidth
            paint.color = line.color
            canvas?.drawLine(line.startX, line.startY, line.endX, line.endY, paint)
        }
    }

    private fun drawTurtle() {
        val turtle = commandInterpreter.canvasState.turtle
        val matrix = Matrix()
        matrix.postTranslate(- turtleIcon.width/2F, - turtleIcon.height/2F)
        matrix.postRotate(turtle.direction.toFloat())
        matrix.postTranslate(turtle.x.toFloat(), turtle.y.toFloat())
        canvas?.drawBitmap(turtleIcon, matrix, null)
    }

    fun addCommand(entry: String) {
        commandInterpreter.interpret(entry)
    }

    fun clearLines() {
        commandInterpreter.canvasState.lines.clear()
    }

}