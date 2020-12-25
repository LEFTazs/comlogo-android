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

    lateinit var codeEnterButton: Button
    lateinit var codeInputBox: EditText
    lateinit var drawCanvas: CanvasView
    lateinit var codeHistoryView: TextView
    lateinit var codeHistoryViewScroll: ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        codeEnterButton = findViewById (R.id.codeEnterButton)
        codeInputBox = findViewById(R.id.codeInputBox)
        drawCanvas = findViewById(R.id.drawCanvas)
        codeHistoryView = findViewById(R.id.codeHistoryView)
        codeHistoryViewScroll = findViewById(R.id.codeHistoryViewScroll)

        if (intent.hasExtra("chosenCommand")) {
            val startingCommand = intent.getStringExtra("chosenCommand")
            codeInputBox.setText(startingCommand)
        }

        setUpShakeSensor()
    }

    fun commandEntered(view: View) {
        var command: String = codeInputBox.text.toString()

        codeHistoryView.text = codeHistoryView.text.toString() + "\n? " + command
        codeHistoryViewScroll.fullScroll(ScrollView.FOCUS_DOWN)

        drawCanvas.addCommand(command)
        drawCanvas.invalidate()
    }

    fun switchToCommandsView(view: View) {
        val intent = Intent(this, CommandsViewActivity::class.java)
        startActivity(intent)
    }

    fun addNewCommand(view: View) {
        codeInputBox.text.toString()
        //TODO
    }

    private fun setUpShakeSensor() {
        val mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val mSensorListener = ShakeDetector(drawCanvas)
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL)
    }
}
