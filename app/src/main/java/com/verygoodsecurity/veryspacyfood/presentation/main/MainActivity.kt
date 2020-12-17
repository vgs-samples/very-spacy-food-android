package com.verygoodsecurity.veryspacyfood.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.test.spacy_food_components.presentation.main.MainNavigationHandler
import com.verygoodsecurity.veryspacyfood.presentation.main.fragment.details.ProductDetailsFragment
import com.verygoodsecurity.veryspacyfood.R
import com.verygoodsecurity.veryspacyfood.presentation.main.model.Product

class MainActivity : AppCompatActivity(), MainNavigationHandler {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_App)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun navigateToDetails(product: Product) {
        val currentFragment = supportFragmentManager.findFragmentByTag(getString(R.string.app_name))
        supportFragmentManager.commit {
            this.add(R.id.fclMainActivity, ProductDetailsFragment(), getString(R.string.app_name))
            this.addToBackStack(null)
            if (currentFragment?.isAdded == true) {
                this.hide(currentFragment)
            }
        }
    }

    override fun navigateBack() {
    }

    companion object {

        val TEST_DATA = listOf(
            Product(
                "Pluto roll",
                "Fresh salmon with avocado, philadelphia, and cucumber, spruced with a pinch of sesame",
                14.49,
                R.drawable.ic_logo
            ),
            Product(
                "Mars Pizza",
                "This pizza is topped with authentic Italian salami, peppers, Parmesan cheese, and spices",
                12.25,
                R.drawable.ic_logo
            ),
            Product(
                "Sunburger",
                "Pure beef topped with a fresh tomato, chopped onions, ketchup, mustard, and a slice of melty cheddar",
                17.15,
                R.drawable.ic_logo
            ),
            Product(
                "Alien noodle",
                "Buttery, garlicky noodles served with a boiled egg, green beans, and juicy jumbo shrimp",
                13.49,
                R.drawable.ic_logo
            ),
            Product(
                "Venus fries",
                "Crunchy potato fries with tomato sauce dip is a nutrishious side dish",
                8.99,
                R.drawable.ic_logo
            )
        )
    }
}