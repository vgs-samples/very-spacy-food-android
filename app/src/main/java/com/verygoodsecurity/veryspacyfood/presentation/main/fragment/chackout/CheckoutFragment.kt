package com.verygoodsecurity.veryspacyfood.presentation.main.fragment.chackout

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import com.verygoodsecurity.veryspacyfood.R
import com.verygoodsecurity.veryspacyfood.domain.Result
import com.verygoodsecurity.veryspacyfood.presentation.main.fragment.core.BaseMainFragment
import com.verygoodsecurity.veryspacyfood.presentation.main.fragment.loading.LoadingDialogFragment
import com.verygoodsecurity.veryspacyfood.presentation.main.viewmodel.MainViewModel
import com.verygoodsecurity.veryspacyfood.util.extension.hide
import com.verygoodsecurity.veryspacyfood.util.extension.show
import com.verygoodsecurity.veryspacyfood.util.extension.showShort
import kotlinx.android.synthetic.main.fragment_checkout.*
import kotlinx.android.synthetic.main.main_toolbar.*

class CheckoutFragment : BaseMainFragment(R.layout.fragment_checkout),
    LoadingDialogFragment.LoadingDialogClickListener {

    private val viewModel: MainViewModel by activityViewModels()

    override fun initView(savedInstanceState: Bundle?) {
        initToolbar()
        initContent()
        initListeners()
    }

    override fun onCancelClick() {
        hideLoading()
        viewModel.cancelCheckout()
    }

    private fun initToolbar() {
        mtvMainToolbarBack?.show()
        ivMainToolbarBasket?.hide()
    }

    private fun initContent() {
        checkNotNull(viewModel.cartLiveData.value).run {
            mtvCheckoutAmount.text = getString(R.string.all_price_mask, sumByDouble { it.price })
        }
        checkNotNull(viewModel.paymentCardLiveData.value).run {
            mtvCheckoutCardNumber.text = getString(
                R.string.checkout_pay_card_mask,
                cardNumberBin.slice(0..3),
                cardNumberLast4
            )
        }
    }

    private fun initListeners() {
        mtvMainToolbarBack?.setOnClickListener {
            navigation.navigateBack()
        }
        mbCheckoutPay?.setOnClickListener {
            checkout()
        }
    }

    private fun checkout() {
        showLoading(LoadingDialogFragment.Style.FILLED)
        viewModel.checkout {
            hideLoading()
            requireActivity().runOnUiThread {
                when (it) {
                    is Result.Success -> navigation.navigateToCheckoutComplete()
                    is Result.Error -> requireContext().showShort(
                        it.msg ?: getString(R.string.all_error)
                    )
                }
            }
        }
    }

    companion object {

        const val TAG = "CheckoutFragment"
    }
}