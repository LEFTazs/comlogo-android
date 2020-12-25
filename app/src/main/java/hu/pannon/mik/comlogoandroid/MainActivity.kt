package hu.pannon.mik.comlogoandroid

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var codeEnterButton: Button = findViewById (R.id.codeEnterButton)
        var codeInputBox: EditText = findViewById(R.id.codeInputBox)
        var drawCanvas: CanvasView = findViewById(R.id.drawCanvas)
        var codeHistoryView: TextView = findViewById(R.id.codeHistoryView)
        var codeHistoryViewScroll: ScrollView = findViewById(R.id.codeHistoryViewScroll)

        codeEnterButton.setOnClickListener {
            var command: String = codeInputBox.text.toString()

            codeHistoryView.text = codeHistoryView.text.toString() + "\n? " + command
            codeHistoryViewScroll.fullScroll(ScrollView.FOCUS_DOWN)

            drawCanvas.addCommand(command)
            drawCanvas.invalidate()
        }

        val mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val mSensorListener = ShakeDetector(drawCanvas)
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL)
    }

    fun switchToCommandsView(view: View) {
        val intent = Intent(this, CommandsViewActivity::class.java)
        startActivity(intent)
    }

    fun addNewCommand(view: View) {
        var codeInputBox: EditText = findViewById(R.id.codeInputBox)
        codeInputBox.text.toString()
        //TODO
    }


}
