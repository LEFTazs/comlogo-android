package hu.pannon.mik.comlogoandroid

import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.commands_view.*

class CommandsViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.commands_view)

        val recyclerView = findViewById<View>(R.id.commands) as RecyclerView
        val adapter = CommandsAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val dbMediator = DatabaseMediator(adapter)
        dbMediator.execute()
    }
}
