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
        var modifiedTurtleIcon = Bitmap.createScaledBitmap(turtleIcon, turtle.width, turtle.height, false)
        val matrix = Matrix()
        matrix.postRotate(turtle.direction.toFloat())
        modifiedTurtleIcon = Bitmap.createBitmap(modifiedTurtleIcon, 0, 0, modifiedTurtleIcon.width, modifiedTurtleIcon.height, matrix, false)
        var paint = Paint()
        paint.alpha = 254
        canvas?.drawBitmap(modifiedTurtleIcon, turtle.x.toFloat()-20, turtle.y.toFloat()-20, paint)
    }

    fun addCommand(entry: String) {
        val newLines = commandInterpreter.interpret(entry)
        this.lines.addAll(newLines)
    }

}