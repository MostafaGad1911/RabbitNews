package mostafa.projects.grand.utils

import android.app.Activity
import android.content.res.Resources
import android.os.NetworkOnMainThreadException
import android.system.ErrnoException
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import mostafa.projects.grand.GrandApp
import mostafa.projects.grand.R
import okhttp3.ResponseBody
import okhttp3.internal.http2.ConnectionShutdownException
import org.json.JSONObject
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

// type alias brief for resources definition
typealias NewsLayout = R.layout
typealias NewsId = R.id
typealias NewsString = R.string
typealias NewsAnim = R.anim

// Views extensions
fun View.show() {
    this.visibility = View.VISIBLE
}
fun View.hide() {
    this.visibility = View.GONE
}
fun ImageView.displayImg(source: String) {
    Glide.with(this).load(source).fitCenter().diskCacheStrategy(DiskCacheStrategy.DATA).into(this)

}
fun String.capitalize(): String = this.replaceFirstChar { it.uppercase() }
fun RecyclerView.initRecyclerLytManager() {
    this.layoutManager = LinearLayoutManager(
        this.context,
        LinearLayoutManager.VERTICAL,
        false
    )
}
fun Activity.showToast(message:String){
    Toast.makeText(this , message , Toast.LENGTH_SHORT).show()
}

// Fetch error
fun ResponseBody.getErrorMessage(): String {
    val errorJsonString = this.string()
    val json: JSONObject = JSONObject(errorJsonString)
    val msg = json.getString("message")
    return msg
}

fun Throwable.getMessage(): String {
    var msg = ""
    when (this) {
        is SocketTimeoutException -> {
            msg = "Timeout - Please check your internet connection"
        }
        is UnknownHostException -> {
            msg = "Unable to make a connection. Please check your internet"
        }
        is NetworkOnMainThreadException -> {
            msg = "Unable to make a connection. Please check your internet"
        }
        is ConnectException -> {
            msg = "Unable to make a connection. Please check your internet"
        }
        is ConnectionShutdownException -> {
            msg = "Connection shutdown. Please check your internet"
        }
        is ErrnoException -> {
            msg = "Connection shutdown. Please check your internet"
        }
        is IOException -> {
            msg = "Server is unreachable, please try again later."
        }
        is IllegalStateException -> {
            msg = "${this.message}"
        }
        else -> {
            msg = "${this.message}"
        }
    }
    return msg
}
