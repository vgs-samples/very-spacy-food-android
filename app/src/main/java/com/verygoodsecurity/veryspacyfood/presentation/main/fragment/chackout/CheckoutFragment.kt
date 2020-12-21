package com.verygoodsecurity.veryspacyfood.presentation.main.fragment.chackout

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import com.verygoodsecurity.veryspacyfood.R
import com.verygoodsecurity.veryspacyfood.presentation.main.fragment.core.BaseMainFragment
import com.verygoodsecurity.veryspacyfood.presentation.main.viewmodel.MainViewModel
import com.verygoodsecurity.veryspacyfood.util.extension.showShort

class CheckoutFragment : BaseMainFragment(R.layout.fragment_checkout) {

    private val viewModel: MainViewModel by activityViewModels()

    override fun initView(savedInstanceState: Bundle?) {
        requireContext().showShort(viewModel.paymentCardLiveData.value.toString())
    }

    companion object {

        const val TAG = "CheckoutFragment"
    }
}