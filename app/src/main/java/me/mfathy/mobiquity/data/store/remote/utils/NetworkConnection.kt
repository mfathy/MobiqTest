package me.mfathy.mobiquity.data.store.remote.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import javax.inject.Inject


/**
 * Created by Mohammed Fathy
 * dev.mfathy@gmail.com
 *
 * Helper class to check internet connectivity
 */
class NetworkConnection @Inject constructor(val context: Context) {

    /**
     * Check internet connectivity using the android ConnectivityManager
     * Returns true if there is a connection otherwise false.
     */
    fun hasInternetConnection(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(
                NetworkCapabilities.TRANSPORT_CELLULAR
            )
        } else {
            val activeNetwork = cm.activeNetworkInfo
            (activeNetwork != null && activeNetwork.isConnected)
        }
    }
}