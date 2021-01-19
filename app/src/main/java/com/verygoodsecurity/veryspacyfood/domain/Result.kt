package com.verygoodsecurity.veryspacyfood.domain

sealed class Result {

    object Success : Result()

    data class Error constructor(val msg: String? = null) : Result()
}