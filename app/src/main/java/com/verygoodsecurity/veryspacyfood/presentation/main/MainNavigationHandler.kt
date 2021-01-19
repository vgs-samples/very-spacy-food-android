package com.verygoodsecurity.veryspacyfood.presentation.main

import com.verygoodsecurity.veryspacyfood.presentation.main.model.Product

interface MainNavigationHandler {

    fun navigateToDetails(product: Product)

    fun navigateToAddCreditCard()

    fun navigateToCheckout()

    fun navigateToCheckoutComplete()

    fun navigateBack(inclusive: Boolean = false)
}