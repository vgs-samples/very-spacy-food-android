package com.test.spacy_food_components.presentation.main

import com.verygoodsecurity.veryspacyfood.presentation.main.model.Product

interface MainNavigationHandler {

    fun navigateToDetails(product: Product)

    fun navigateBack()
}