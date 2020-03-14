package hu.pannon.mik.comlogoandroid

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var codeEnterButton: Button = findViewById (R.id.codeEnterButton)
        var codeInputBox: EditText = findViewById(R.id.codeInputBox)
        var drawCanvas: CanvasView = findViewById(R.id.drawCanvas)

        codeEnterButton.setOnClickListener {
            //TODO: add command interpreter here
            drawCanvas.addLine(
                Line(
                    10F,
                    10F,
                    100F,
                    100F,
                    10F,
                    Color.RED
                )
            )
            drawCanvas.invalidate()
        }
    }

}
