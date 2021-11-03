package com.verygoodsecurity.veryspacyfood.util

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat

class ConnectivityHelper constructor(private val applicationContext: Context) {

    @SuppressLint("MissingPermission")
    fun isNetworkConnectionAvailable(): Boolean {
        if (ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.ACCESS_NETWORK_STATE
            ) == PackageManager.PERMISSION_DENIED
        ) {
            throw IllegalStateException("Permission ACCESS_NETWORK_STATE is missing in manifest.")
        }
        val manager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE)
        return (manager as? ConnectivityManager)?.activeNetworkInfo != null
    }
}