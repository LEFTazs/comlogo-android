package hu.pannon.mik.comlogoandroid

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.ImageView
import androidx.constraintlayout.solver.widgets.Rectangle


class CanvasView: ImageView {

    constructor(context: Context?): super(context)

    constructor(context: Context?, attrs: AttributeSet?): super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int): super(context, attrs, defStyle)

    var lines: MutableList<Line> = mutableListOf()

    var commandInterpreter = CommandInterpreter()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        for (line: Line in lines) {
            val paint = Paint()
            paint.strokeWidth = line.strokeWidth
            paint.color = line.color
            canvas?.drawLine(line.startX, line.startY, line.endX, line.endY, paint)
        }

        var turtleIcon: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.turtle)
        val resize = 4
        turtleIcon = Bitmap.createScaledBitmap(turtleIcon, 15*resize, 23*resize, false)
        val matrix = Matrix()
        val turtle = commandInterpreter.canvasState.turtle
        matrix.postRotate(turtle.direction.toFloat())
        turtleIcon = Bitmap.createBitmap(turtleIcon, 0, 0, turtleIcon.width, turtleIcon.height, matrix, true)
        canvas?.drawBitmap(turtleIcon, turtle.x.toFloat(), turtle.y.toFloat(), Paint())
    }

    fun addCommand(entry: String) {
        val newLines = commandInterpreter.interpret(entry)
        this.lines.addAll(newLines)
    }

}