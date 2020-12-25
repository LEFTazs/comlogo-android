package hu.pannon.mik.comlogoandroid

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CommandRegistererActivity : AppCompatActivity() {

    private lateinit var titleText: EditText
    private lateinit var commandText: EditText
    private lateinit var madebyText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.command_register)

        titleText = findViewById(R.id.titleText)
        commandText = findViewById(R.id.commandText)
        madebyText = findViewById(R.id.madebyText)

        if (intent.hasExtra("commandToSave")) {
            val commandToSave = intent.getStringExtra("commandToSave")
            commandText.setText(commandToSave)
        }
    }

    fun sendCommand(view: View) {
        val title = titleText.text.toString()
        val command = commandText.text.toString()
        val madeby = madebyText.text.toString()

        val databasePoster = DatabasePoster(this)
        databasePoster.execute(title, command, madeby)
    }
}
