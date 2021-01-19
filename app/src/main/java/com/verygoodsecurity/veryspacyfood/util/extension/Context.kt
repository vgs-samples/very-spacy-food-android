package com.verygoodsecurity.veryspacyfood.util.extension

import android.Manifest
import android.content.Context
import android.content.res.ColorStateList
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.RequiresPermission
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

fun Context.getColorStateListSafe(@ColorRes id: Int) =
    ColorStateList.valueOf(ContextCompat.getColor(this, id))

fun Context.showShort(message: String) {
    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
}

fun Context.showShort(@StringRes id: Int) {
    Toast.makeText(applicationContext, getString(id), Toast.LENGTH_SHORT).show()
}

@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
fun Context.isNetworkConnectionAvailable(): Boolean {
    val manager = getSystemService(Context.CONNECTIVITY_SERVICE)
    return (manager as? ConnectivityManager)?.activeNetworkInfo != null
}