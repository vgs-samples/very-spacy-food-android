package com.verygoodsecurity.veryspacyfood.presentation.core

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes id: Int) : Fragment(id) {

    protected abstract fun initView(savedInstanceState: Bundle?)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView(savedInstanceState)
    }
}