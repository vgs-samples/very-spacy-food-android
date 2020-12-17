package com.verygoodsecurity.veryspacyfood.presentation.main.model

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

data class Product constructor(
    val title: String,
    val description: String,
    val price: Double,
    @DrawableRes val image: Int,
    @ColorRes val associatedColor: Int
)