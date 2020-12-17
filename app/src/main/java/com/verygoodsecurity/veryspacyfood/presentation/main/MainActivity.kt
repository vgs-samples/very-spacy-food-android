package com.verygoodsecurity.veryspacyfood.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.verygoodsecurity.veryspacyfood.R
import com.verygoodsecurity.veryspacyfood.presentation.main.fragment.details.ProductDetailsFragment
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
}