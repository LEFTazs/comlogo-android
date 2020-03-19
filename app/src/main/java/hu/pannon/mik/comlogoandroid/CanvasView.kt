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

    var lines: MutableList<Line> = mutableListOf()

    var commandInterpreter = CommandInterpreter()

    var turtleIcon: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.turtle)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        for (line: Line in lines) {
            val paint = Paint()
            paint.strokeWidth = line.strokeWidth
            paint.color = line.color
            canvas?.drawLine(line.startX, line.startY, line.endX, line.endY, paint)
        }

        val turtle = commandInterpreter.canvasState.turtle
        val matrix = Matrix()
        matrix.postTranslate(- turtleIcon.width/2F, - turtleIcon.height/2F)
        matrix.postRotate(turtle.direction.toFloat())
        matrix.postTranslate(turtle.x.toFloat(), turtle.y.toFloat())
        canvas?.drawBitmap(turtleIcon, matrix, null)
    }

    fun addCommand(entry: String) {
        val newLines = commandInterpreter.interpret(entry)
        this.lines.addAll(newLines)
    }

}