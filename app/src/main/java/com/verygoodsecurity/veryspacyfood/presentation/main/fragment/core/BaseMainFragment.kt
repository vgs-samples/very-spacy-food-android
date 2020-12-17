package com.verygoodsecurity.veryspacyfood.presentation.main.fragment.core

import android.content.Context
import androidx.annotation.LayoutRes
import com.verygoodsecurity.veryspacyfood.presentation.main.MainNavigationHandler
import com.verygoodsecurity.veryspacyfood.presentation.core.BaseFragment

abstract class BaseMainFragment(@LayoutRes id: Int) : BaseFragment(id) {

    protected lateinit var navigation: MainNavigationHandler

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigation = (context as? MainNavigationHandler)
            ?: throw IllegalStateException("Activity should implement MainNavigationHandler")
    }
}