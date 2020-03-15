package hu.pannon.mik.comlogoandroid

import android.graphics.Color

class CommandInterpreter {
    var canvasState = CanvasState()
    var lines: MutableList<Line> = mutableListOf()

    fun interpret(entry: String): MutableList<Line> {
        lines.clear()

        val commandParts = entry.split(" ")
        val iterator = commandParts.iterator()
        while (iterator.hasNext()) {
            val lastX = canvasState.turtle.x.toFloat()
            val lastY = canvasState.turtle.y.toFloat()

            val command = iterator.next()
            when (command) {
                "előre", "e" -> commandMoveForward(iterator)
                "hátra", "h" -> commandMoveBackwards(iterator)
            }

            val newX = canvasState.turtle.x.toFloat()
            val newY = canvasState.turtle.y.toFloat()
            val newLine = Line(lastX, lastY, newX, newY, canvasState.penStrokeWidth, canvasState.penColor)
            if (canvasState.isPenDown)
                lines.add(newLine)
        }

        return lines
    }

    private fun commandMoveForward(iterator: Iterator<String>) {
        val distance = iterator.next().toDouble()
        canvasState.turtle.moveForward(distance)
    }

    private fun commandMoveBackwards(iterator: Iterator<String>) {
        val distance = iterator.next().toDouble() * -1
        canvasState.turtle.moveForward(distance)
    }
}