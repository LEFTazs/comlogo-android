package hu.pannon.mik.comlogoandroid

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.ImageView


class CanvasView: ImageView {

    constructor(context: Context?): super(context)

    constructor(context: Context?, attrs: AttributeSet?): super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int): super(context, attrs, defStyle)

    var lines: MutableList<Line> = mutableListOf()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        for (line: Line in lines) {
            val paint = Paint()
            paint.strokeWidth = line.strokeWidth
            paint.color = line.color
            canvas?.drawLine(line.startX, line.startY, line.endX, line.endY, paint)
        }
    }

    fun addLine(line: Line) {
        lines.add(line)
    }

}