package com.verygoodsecurity.veryspacyfood.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.verygoodsecurity.veryspacyfood.R
import com.verygoodsecurity.veryspacyfood.presentation.main.fragment.chackout.CheckoutFragment
import com.verygoodsecurity.veryspacyfood.presentation.main.fragment.complete.CheckoutCompleteFragment
import com.verygoodsecurity.veryspacyfood.presentation.main.fragment.details.ProductDetailsFragment
import com.verygoodsecurity.veryspacyfood.presentation.main.fragment.payment.CreditCardFragment
import com.verygoodsecurity.veryspacyfood.presentation.main.model.Product

class MainActivity : AppCompatActivity(), MainNavigationHandler {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_App)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        with(supportFragmentManager.findFragmentByTag(CheckoutCompleteFragment.TAG)) {
            if (this != null) {
                navigateBack(true)
            } else {
                super.onBackPressed()
            }
        }
    }

    override fun navigateToDetails(product: Product) {
        supportFragmentManager.commit {
            replace(
                R.id.fclMainActivity,
                ProductDetailsFragment.newInstance(product),
                ProductDetailsFragment.TAG
            )
            addToBackStack(null)
        }
    }

    override fun navigateToAddCreditCard() {
        CreditCardFragment().show(supportFragmentManager, CreditCardFragment.TAG)
    }

    override fun navigateToCheckout() {
        supportFragmentManager.commit {
            replace(R.id.fclMainActivity, CheckoutFragment(), CheckoutFragment.TAG)
            addToBackStack(null)
        }
    }

    override fun navigateToCheckoutComplete() {
        supportFragmentManager.commit {
            replace(R.id.fclMainActivity, CheckoutCompleteFragment(), CheckoutCompleteFragment.TAG)
            addToBackStack(null)
        }
    }

    override fun navigateBack(inclusive: Boolean) {
        if (inclusive) {
            supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            return
        }
        supportFragmentManager.popBackStack()
    }
}