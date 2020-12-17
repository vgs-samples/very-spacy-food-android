package com.verygoodsecurity.veryspacyfood.presentation.main.fragment.details

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import com.verygoodsecurity.veryspacyfood.R
import com.verygoodsecurity.veryspacyfood.presentation.main.fragment.core.BaseMainFragment
import com.verygoodsecurity.veryspacyfood.presentation.main.viewmodel.MainViewModel

class ProductDetailsFragment : BaseMainFragment(R.layout.fragment_product_details) {

    @Suppress("unused")
    private val viewModel: MainViewModel by activityViewModels()

    override fun initView(savedInstanceState: Bundle?) {

    }
}