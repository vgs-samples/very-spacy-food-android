package com.verygoodsecurity.veryspacyfood.data.dto

import com.google.gson.annotations.SerializedName

class Transaction constructor(
    @SerializedName("userId") private val userId: String,
    @SerializedName("amount") private val amount: Int
)