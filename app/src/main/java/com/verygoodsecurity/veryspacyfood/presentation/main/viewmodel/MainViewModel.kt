package com.verygoodsecurity.veryspacyfood.presentation.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.verygoodsecurity.veryspacyfood.presentation.main.model.Product
import com.verygoodsecurity.veryspacyfood.presentation.main.model.SecureCard

class MainViewModel : ViewModel() {

    private val _cartLiveData = MutableLiveData<ArrayList<Product>>(ArrayList())
    val cartLiveData: LiveData<ArrayList<Product>> get() = _cartLiveData

    private val _paymentCardLiveData = MutableLiveData<SecureCard>()
    val paymentCardLiveData: LiveData<SecureCard> get() = _paymentCardLiveData

    fun addToCart(product: Product) {
        _cartLiveData.value?.add(product)
        _cartLiveData.value = _cartLiveData.value
    }

    fun addPaymentMethod(card: SecureCard?) {
        _paymentCardLiveData.value = card
    }
}