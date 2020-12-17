package com.verygoodsecurity.veryspacyfood.presentation.main

import com.verygoodsecurity.veryspacyfood.presentation.main.model.Product

interface MainNavigationHandler {

    fun navigateToDetails(product: Product)
}