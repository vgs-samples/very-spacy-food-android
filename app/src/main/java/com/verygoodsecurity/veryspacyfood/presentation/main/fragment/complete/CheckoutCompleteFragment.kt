package com.verygoodsecurity.veryspacyfood.presentation.main.fragment.complete

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import com.verygoodsecurity.veryspacyfood.R
import com.verygoodsecurity.veryspacyfood.presentation.main.fragment.core.BaseMainFragment
import com.verygoodsecurity.veryspacyfood.presentation.main.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_checkout_complete.*

class CheckoutCompleteFragment : BaseMainFragment(R.layout.fragment_checkout_complete) {

    private val viewModel: MainViewModel by activityViewModels()

    override fun initView(savedInstanceState: Bundle?) {
        initContent()
        initListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.clearData()
    }

    private fun initListeners() {
        ivCheckoutCompleteClose?.setOnClickListener {
            navigation.navigateBack(inclusive = true)
        }
    }

    private fun initContent() {
        checkNotNull(viewModel.cartLiveData.value).run {
            mtvCheckoutCompletePrice.text =
                getString(R.string.all_price_mask, sumByDouble { it.price })
        }
        checkNotNull(viewModel.paymentCardLiveData.value).run {
            mtvCheckoutCompleteCardNumber.text =
                getString(
                    R.string.checkout_pay_card_mask,
                    cardNumberBin.slice(0..3),
                    cardNumberLast4
                )
        }
    }

    companion object {

        const val TAG = "CheckoutCompleteFragment"
    }
}