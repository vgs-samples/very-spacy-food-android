package com.verygoodsecurity.veryspacyfood.presentation.main.fragment.core

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import com.verygoodsecurity.veryspacyfood.presentation.core.BaseFragment
import com.verygoodsecurity.veryspacyfood.presentation.main.MainNavigationHandler
import com.verygoodsecurity.veryspacyfood.presentation.main.fragment.loading.LoadingDialogFragment

abstract class BaseMainFragment(@LayoutRes id: Int) : BaseFragment(id) {

    protected lateinit var navigation: MainNavigationHandler

    private var loadingDialog: DialogFragment? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigation = (context as? MainNavigationHandler)
            ?: throw IllegalStateException("Activity should implement MainNavigationHandler")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        hideLoading()
    }

    fun showLoading(style: LoadingDialogFragment.Style) {
        loadingDialog?.dismiss()
        loadingDialog = LoadingDialogFragment.newInstance(style)
        loadingDialog?.show(requireActivity().supportFragmentManager, null)
    }

    fun hideLoading() {
        loadingDialog?.dismiss()
    }
}