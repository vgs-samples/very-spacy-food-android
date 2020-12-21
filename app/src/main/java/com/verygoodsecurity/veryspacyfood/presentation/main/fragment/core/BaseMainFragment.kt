package com.verygoodsecurity.veryspacyfood.presentation.main.fragment.core

import android.content.Context
import androidx.annotation.LayoutRes
import com.verygoodsecurity.veryspacyfood.presentation.core.BaseFragment
import com.verygoodsecurity.veryspacyfood.presentation.main.LoadingHandler
import com.verygoodsecurity.veryspacyfood.presentation.main.MainNavigationHandler

abstract class BaseMainFragment(@LayoutRes id: Int) : BaseFragment(id) {

    protected lateinit var navigation: MainNavigationHandler
    protected lateinit var loadingHandler: LoadingHandler

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigation = (context as? MainNavigationHandler)
            ?: throw IllegalStateException("Activity should implement MainNavigationHandler")
        loadingHandler = (context as? LoadingHandler)
            ?: throw IllegalStateException("Activity should implement LoadingHandler")
    }
}