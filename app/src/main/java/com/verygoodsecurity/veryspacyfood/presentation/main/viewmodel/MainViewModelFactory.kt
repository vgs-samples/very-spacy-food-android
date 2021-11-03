package com.verygoodsecurity.veryspacyfood.presentation.main.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.verygoodsecurity.veryspacyfood.util.ConnectivityHelper

class MainViewModelFactory constructor(
    private val applicationContext: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ConnectivityHelper::class.java)
            .newInstance(ConnectivityHelper(applicationContext))
    }
}