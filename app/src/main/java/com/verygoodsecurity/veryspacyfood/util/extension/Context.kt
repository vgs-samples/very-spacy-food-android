package com.verygoodsecurity.veryspacyfood.util.extension

import android.content.Context
import android.content.res.ColorStateList
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun Context.getColorStateListSafe(@ColorRes id: Int) =
    ColorStateList.valueOf(ContextCompat.getColor(this, id))