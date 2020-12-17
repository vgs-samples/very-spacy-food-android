package com.verygoodsecurity.veryspacyfood.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.verygoodsecurity.veryspacyfood.R
import com.verygoodsecurity.veryspacyfood.presentation.main.fragment.details.ProductDetailsFragment
import com.verygoodsecurity.veryspacyfood.presentation.main.fragment.list.ProductListFragment
import com.verygoodsecurity.veryspacyfood.presentation.main.model.Product

class MainActivity : AppCompatActivity(), MainNavigationHandler {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_App)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun navigateToDetails(product: Product) {
        with(supportFragmentManager) {
            val tag = getString(R.string.main_fragment_tag)
            val currentFragment = findFragmentByTag(tag)
            commit {
                add(R.id.fclMainActivity, ProductDetailsFragment.newInstance(product), tag)
                addToBackStack(null)
                if (currentFragment?.isAdded == true && currentFragment is ProductListFragment) {
                    hide(currentFragment)
                }
            }
        }
    }

    override fun navigateBack() {
        supportFragmentManager.popBackStack()
    }
}