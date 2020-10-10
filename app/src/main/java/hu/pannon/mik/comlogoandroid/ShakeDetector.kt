package hu.pannon.mik.comlogoandroid

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager


class ShakeDetector constructor(val drawCanvas: CanvasView) : SensorEventListener {
    private var mAccel = 10f
    private var mAccelCurrent = SensorManager.GRAVITY_EARTH
    private var mAccelLast = SensorManager.GRAVITY_EARTH

    override fun onSensorChanged(event: SensorEvent?) {
        val x = event!!.values[0]
        val y = event.values[1]
        val z = event.values[2]
        mAccelLast = mAccelCurrent
        mAccelCurrent = Math.sqrt((x * x + y * y + z * z).toDouble()).toFloat()
        val delta: Float = mAccelCurrent - mAccelLast
        mAccel = mAccel * 0.9f + delta
        if (mAccel > 6) {
            drawCanvas.clearLines()
            drawCanvas.invalidate()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

}