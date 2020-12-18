package com.verygoodsecurity.veryspacyfood.presentation.main.model

data class SecureCard constructor(
    val cardNumberAlias: String,
    val cvcAlias: String,
    val expAlias: String,
    val cardNumberBin: String,
    val cardNumberLast4: String,
    val cardBrand: String,
)