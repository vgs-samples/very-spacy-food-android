package com.verygoodsecurity.veryspacyfood.presentation.main.model

import android.os.Parcelable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product constructor(
    val title: String,
    val description: String,
    val weight: Int,
    val price: Double,
    @DrawableRes val image: Int,
    @ColorRes val associatedColor: Int
) : Parcelable