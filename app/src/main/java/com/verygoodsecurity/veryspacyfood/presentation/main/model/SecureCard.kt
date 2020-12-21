package com.verygoodsecurity.veryspacyfood.presentation.main.model

data class SecureCard constructor(
    val cardNumberBin: String,
    val cardNumberLast4: String,
    val cardBrand: String,
)