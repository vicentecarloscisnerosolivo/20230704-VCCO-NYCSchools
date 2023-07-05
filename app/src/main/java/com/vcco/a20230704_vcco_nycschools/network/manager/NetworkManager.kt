package com.vcco.a20230704_vcco_nycschools.network.manager

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import javax.inject.Inject


/**
 * Custom Class that verify the network status of the device
 */
class NetworkManager @Inject constructor(private val connectivityManager: ConnectivityManager) {
    /**
     * Method Which detect the device state, use for handle online synchronization
     * @return Boolean
     */
    fun isOnLine() =
        connectivityManager.let {
            val networkCapabilities = it.activeNetwork ?: return false
            val activeNetwork =
                it.getNetworkCapabilities(networkCapabilities) ?: return false
            when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }
}