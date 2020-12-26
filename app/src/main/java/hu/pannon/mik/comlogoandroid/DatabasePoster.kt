package hu.pannon.mik.comlogoandroid

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.net.URL
import java.net.HttpURLConnection
import java.nio.charset.StandardCharsets


class DatabasePoster(private val context: Context) : AsyncTask<String, Void, Unit>() {

    private val url = URL("http://comlogowebserver.nhely.hu/addrow.php")

    override fun doInBackground(vararg params: String?) {
        val title = params[0]
        val command = params[1]
        val madeby = params[2]
        val parameters = "title=" + (title) + "&command=" + (command) + "&madeby=" + (madeby)
        val postData = parameters.toByteArray(StandardCharsets.UTF_8)
        with(url.openConnection() as HttpURLConnection) {
            requestMethod = "POST"
            setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
            setRequestProperty("charset", "utf-8")
            setRequestProperty("Content-Length", postData.size.toString())
            doOutput = true
            useCaches = false

            outputStream.write(postData)

            var answer = ""
            inputStream.bufferedReader().forEachLine {
                answer = answer.plus(it)
            }
            println(answer)
        }
    }

    override fun onPostExecute(result: Unit?) {
        super.onPostExecute(result)

        Toast.makeText(context, "Küldés befejezve.", Toast.LENGTH_LONG).show()

        (context as AppCompatActivity).finish()
    }
}