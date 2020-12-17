package com.verygoodsecurity.veryspacyfood.util

import com.verygoodsecurity.veryspacyfood.R
import com.verygoodsecurity.veryspacyfood.presentation.main.model.Product

object DataProvider {

    val TEST_DATA = listOf(
        Product(
            "Pluto roll",
            "Fresh salmon with avocado, philadelphia, and cucumber, spruced with a pinch of sesame",
            14.49,
            R.drawable.ic_logo,
            R.color.paleVioletRed
        ),
        Product(
            "Mars Pizza",
            "This pizza is topped with authentic Italian salami, peppers, Parmesan cheese, and spices",
            12.25,
            R.drawable.ic_logo,
            R.color.fuzzyWuzzyBrown
        ),
        Product(
            "Sunburger",
            "Pure beef topped with a fresh tomato, chopped onions, ketchup, mustard, and a slice of melty cheddar",
            17.15,
            R.drawable.ic_logo,
            R.color.sandyBrown
        ),
        Product(
            "Alien noodle",
            "Buttery, garlicky noodles served with a boiled egg, green beans, and juicy jumbo shrimp",
            13.49,
            R.drawable.ic_logo,
            R.color.niagara
        ),
        Product(
            "Venus fries",
            "Crunchy potato fries with tomato sauce dip is a nutrishious side dish",
            8.99,
            R.drawable.ic_logo,
            R.color.goldenTainoi
        )
    )
}