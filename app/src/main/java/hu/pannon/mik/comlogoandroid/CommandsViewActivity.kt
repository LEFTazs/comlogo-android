package hu.pannon.mik.comlogoandroid

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CommandsViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.commands_view)

        val recyclerView = findViewById<View>(R.id.commands) as RecyclerView
        val adapter = CommandsAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val dbMediator = DatabaseGetter(adapter)
        dbMediator.execute()

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(
            object : OnItemClickListener, ItemClickSupport.OnItemClickListener {
                override fun onItemClicked(recyclerView: RecyclerView?, position: Int, v: View?) {
                    val chosenCommand: String = adapter.getItem(position).command
                    val intent = Intent(this@CommandsViewActivity, MainActivity::class.java).apply {
                        putExtra("chosenCommand", chosenCommand)
                    }
                    startActivity(intent)
                }

                override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                }
            }
        )
    }
}
