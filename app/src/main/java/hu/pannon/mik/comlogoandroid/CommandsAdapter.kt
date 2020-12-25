package hu.pannon.mik.comlogoandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CommandsAdapter (private var commands: ArrayList<SavedCommand> = ArrayList()) : RecyclerView.Adapter<CommandsAdapter.ViewHolder>()
{
    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val titleText = itemView.findViewById<TextView>(R.id.title)
        val commandText = itemView.findViewById<TextView>(R.id.command)
        val madebyText = itemView.findViewById<TextView>(R.id.madeby)
        val uploadtimeText = itemView.findViewById<TextView>(R.id.uploadtime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.command_line, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val command: SavedCommand = commands.get(position)
        viewHolder.titleText.setText("Név: " + command.title)
        viewHolder.commandText.setText(command.command)
        viewHolder.madebyText.setText("Készítette: " + command.madeby)
        viewHolder.uploadtimeText.setText("Frissítve: " + command.uploadtime)
    }

    override fun getItemCount(): Int {
        return commands.size
    }

    fun addAll(newCommands: MutableList<SavedCommand>) {
        commands.addAll(newCommands)
        notifyDataSetChanged()
    }
}