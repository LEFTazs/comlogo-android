package hu.pannon.mik.comlogoandroid

import kotlin.math.cos
import kotlin.math.sin

class Turtle(var x: Double, var y: Double) {
    var isPenDown: Boolean = true
    var direction: Double = 0.0
    set(value) {
        field = value
        adjustDirection()
    }

    fun getDirectionInRadian() = degreeToRadian(direction)

    private fun degreeToRadian(degree: Double) = degree * Math.PI / 180.0

    fun rotate(degrees: Double) {
        direction += degrees
        adjustDirection()
    }

    private fun adjustDirection() {
        if (direction >= 360)
            direction -= 360
        if (direction < 0)
            direction += 360
    }

    fun move(x: Double, y: Double) {
        this.x += x
        this.y += y
    }

    fun moveForward(distance: Double) {
        val direction = getDirectionInRadian()
        val xDistance = distance * sin(direction)
        val yDistance = distance * cos(direction) * -1

        this.move(xDistance, yDistance)
    }
}