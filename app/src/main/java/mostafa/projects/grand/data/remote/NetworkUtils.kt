package mostafa.projects.grand.data.remote

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * Created by ESLAM on 07/07/18.
 */

object NetworkUtils {

    const val CACHE_MAX_STALE = 60 * 60 * 24 * 7 //1 week
    const val CACHE_MAX_AGE = 0 //0 seconds
    fun isNetworkConnected(context: Context): Boolean? {
        var isConnected: Boolean? = false // Initial Value
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }
}
