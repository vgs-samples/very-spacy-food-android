package com.verygoodsecurity.veryspacyfood.util

import com.verygoodsecurity.veryspacyfood.R
import com.verygoodsecurity.veryspacyfood.presentation.main.model.Product
import java.util.*

object DataProvider {

    val USER_ID: String = UUID.randomUUID().toString()

    val TEST_DATA = listOf(
        Product(
            "Pluto roll",
            "Fresh salmon with avocado, philadelphia, and cucumber, spruced with a pinch of sesame",
            180,
            14.49,
            R.drawable.pluto_roll,
            R.color.paleVioletRed
        ),
        Product(
            "Mars Pizza",
            "This pizza is topped with authentic Italian salami, peppers, Parmesan cheese, and spices",
            350,
            12.25,
            R.drawable.mars_pizza,
            R.color.fuzzyWuzzyBrown
        ),
        Product(
            "Sunburger",
            "Pure beef topped with a fresh tomato, chopped onions, ketchup, mustard, and a slice of melty cheddar",
            340,
            17.15,
            R.drawable.sunburger,
            R.color.sandyBrown
        ),
        Product(
            "Alien noodle",
            "Buttery, garlicky noodles served with a boiled egg, green beans, and juicy jumbo shrimp",
            330,
            13.49,
            R.drawable.alien_noodles,
            R.color.niagara
        ),
        Product(
            "Venus fries",
            "Crunchy potato fries with tomato sauce dip is a nutrishious side dish",
            270,
            8.99,
            R.drawable.venus_fries,
            R.color.goldenTainoi
        )
    )
}