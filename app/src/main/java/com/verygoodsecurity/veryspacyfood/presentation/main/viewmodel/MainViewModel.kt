package com.verygoodsecurity.veryspacyfood.presentation.main.viewmodel

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var counter: Int = 0

    fun echo(): String {
        counter++
        return "MainViewModel echo"
    }
}