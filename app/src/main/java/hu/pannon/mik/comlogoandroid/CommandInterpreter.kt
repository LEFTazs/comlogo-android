package hu.pannon.mik.comlogoandroid

import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi

class CommandInterpreter {
    var canvasState = CanvasState()

    private lateinit var commandIterator: Iterator<String>

    fun interpret(entry: String) {
        val commandParts = entry.split(" ")
        commandIterator = commandParts.iterator()
        while (commandIterator.hasNext()) {
            executeCommand()
        }
    }

    private fun executeCommand() {
        val command = commandIterator.next()
        when (command) {
            "előre", "e" -> commandMoveForward()
            "hátra", "h" -> commandMoveBackwards()
            "jobbra", "j" -> commandTurnRight()
            "balra", "b" -> commandTurnLeft()
            "tollatle", "tl" -> commandPenOn()
            "tollatfel", "tf" -> commandPenOff()
            "törölrajzlap", "tr", "törölképernyő", "törölkép" -> commandClearCanvas()
            "ismétlés", "ism" -> commandRepeat()
            "haza" -> commandHome()
        }
    }

    private fun commandMoveForward() {
        val lastX = canvasState.turtle.x.toFloat()
        val lastY = canvasState.turtle.y.toFloat()

        val distance = commandIterator.next().toDouble()
        canvasState.turtle.moveForward(distance)

        val newX = canvasState.turtle.x.toFloat()
        val newY = canvasState.turtle.y.toFloat()
        val newLine = Line(lastX, lastY, newX, newY, canvasState.penStrokeWidth, canvasState.penColor)
        if (canvasState.isPenDown)
            canvasState.lines.add(newLine)
    }

    private fun commandMoveBackwards() {
        val lastX = canvasState.turtle.x.toFloat()
        val lastY = canvasState.turtle.y.toFloat()

        val distance = commandIterator.next().toDouble() * -1
        canvasState.turtle.moveForward(distance)

        val newX = canvasState.turtle.x.toFloat()
        val newY = canvasState.turtle.y.toFloat()
        val newLine = Line(lastX, lastY, newX, newY, canvasState.penStrokeWidth, canvasState.penColor)
        if (canvasState.isPenDown)
            canvasState.lines.add(newLine)
    }

    private fun commandTurnRight() {
        val lastX = canvasState.turtle.x.toFloat()
        val lastY = canvasState.turtle.y.toFloat()

        val degrees = commandIterator.next().toDouble()
        canvasState.turtle.rotate(degrees)

        val newX = canvasState.turtle.x.toFloat()
        val newY = canvasState.turtle.y.toFloat()
        val newLine = Line(lastX, lastY, newX, newY, canvasState.penStrokeWidth, canvasState.penColor)
        if (canvasState.isPenDown)
            canvasState.lines.add(newLine)
    }

    private fun commandTurnLeft() {
        val lastX = canvasState.turtle.x.toFloat()
        val lastY = canvasState.turtle.y.toFloat()

        val degrees = commandIterator.next().toDouble() * -1
        canvasState.turtle.rotate(degrees)

        val newX = canvasState.turtle.x.toFloat()
        val newY = canvasState.turtle.y.toFloat()
        val newLine = Line(lastX, lastY, newX, newY, canvasState.penStrokeWidth, canvasState.penColor)
        if (canvasState.isPenDown)
            canvasState.lines.add(newLine)
    }

    private fun commandPenOn() {
        canvasState.isPenDown = true
    }

    private fun commandPenOff() {
        canvasState.isPenDown = false
    }

    private fun commandClearCanvas() {
        canvasState.lines.clear()
    }

    private fun commandRepeat() {
        val times = commandIterator.next().toInt()
        val remainingCommands = commandIterator.asSequence().joinToString(" ")
        val originalIterator = commandIterator
        for (i in 0..times) {
            interpret(remainingCommands)
        }
        commandIterator = originalIterator
    }

    private fun commandHome() {
        canvasState.turtle.jump(400.0, 500.0)
    }
}