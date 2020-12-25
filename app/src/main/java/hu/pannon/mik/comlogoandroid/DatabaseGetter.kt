package hu.pannon.mik.comlogoandroid

import android.os.AsyncTask
import com.google.gson.Gson
import java.net.URL
import java.net.HttpURLConnection


class DatabaseGetter(private var adapter: CommandsAdapter) : AsyncTask<String, Void, MutableList<SavedCommand>>() {

    private val url = URL("http://comlogowebserver.nhely.hu/")
    private val gson = Gson()


    override fun doInBackground(vararg params: String?): MutableList<SavedCommand> {
        with(url.openConnection() as HttpURLConnection) {
            requestMethod = "GET"

            var answer = ""
            inputStream.bufferedReader().forEachLine {
                answer = answer.plus(it)
            }

            return gson.fromJson(answer, Array<SavedCommand>::class.java).toMutableList()
        }
    }

    override fun onPostExecute(result: MutableList<SavedCommand>) {
        adapter.addAll(result)
        super.onPostExecute(result)
    }
}