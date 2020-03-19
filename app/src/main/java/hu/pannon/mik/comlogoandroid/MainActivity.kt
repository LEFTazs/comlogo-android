package hu.pannon.mik.comlogoandroid

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ScrollView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat

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
    }

}
