package com.verygoodsecurity.veryspacyfood.presentation.main.fragment.chackout

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import com.verygoodsecurity.veryspacyfood.R
import com.verygoodsecurity.veryspacyfood.presentation.main.fragment.core.BaseMainFragment
import com.verygoodsecurity.veryspacyfood.presentation.main.viewmodel.MainViewModel
import com.verygoodsecurity.veryspacyfood.util.extension.hide
import com.verygoodsecurity.veryspacyfood.util.extension.show
import com.verygoodsecurity.veryspacyfood.util.extension.showShort
import kotlinx.android.synthetic.main.main_toolbar.*

class CheckoutFragment : BaseMainFragment(R.layout.fragment_checkout) {

    private val viewModel: MainViewModel by activityViewModels()

    override fun initView(savedInstanceState: Bundle?) {
        initToolbar()
        initContent()
        initListeners()
    }

    private fun initToolbar() {
        mtvMainToolbarBack?.show()
        ivMainToolbarBasket?.hide()
    }

    private fun initContent() {
        requireContext().showShort(viewModel.paymentCardLiveData.value.toString())
    }

    private fun initListeners() {
        mtvMainToolbarBack?.setOnClickListener {
            navigation.navigateBack()
        }
    }

    companion object {

        const val TAG = "CheckoutFragment"
    }
}