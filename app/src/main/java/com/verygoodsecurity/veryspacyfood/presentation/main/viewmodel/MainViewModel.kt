package com.verygoodsecurity.veryspacyfood.presentation.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.verygoodsecurity.veryspacyfood.domain.Result
import com.verygoodsecurity.veryspacyfood.domain.repository.CheckoutRepository
import com.verygoodsecurity.veryspacyfood.presentation.main.model.Product
import com.verygoodsecurity.veryspacyfood.presentation.main.model.SecureCard
import com.verygoodsecurity.veryspacyfood.util.ConnectivityHelper
import com.verygoodsecurity.veryspacyfood.util.DataProvider

class MainViewModel constructor(private val connectivityHelper: ConnectivityHelper) : ViewModel() {

    private val repository = CheckoutRepository()

    private val _cartLiveData = MutableLiveData<ArrayList<Product>?>(ArrayList())
    val cartLiveData: LiveData<ArrayList<Product>?> get() = _cartLiveData

    private val _paymentCardLiveData = MutableLiveData<SecureCard?>()
    val paymentCardLiveData: LiveData<SecureCard?> get() = _paymentCardLiveData

    override fun onCleared() {
        super.onCleared()
        repository.cancel()
    }

    fun addToCart(product: Product) {
        val products = _cartLiveData.value ?: ArrayList()
        products.add(product)
        _cartLiveData.value = products
    }

    fun addPaymentMethod(card: SecureCard?) {
        _paymentCardLiveData.value = card
    }

    fun checkout(onResult: (Result) -> Unit) {
        if (!connectivityHelper.isNetworkConnectionAvailable()) {
            onResult.invoke(Result.Error("No internet connection!"))
            return
        }
        val amount = cartLiveData.value?.sumOf { it.price }
        if (amount == null) {
            onResult.invoke(Result.Error("Amount is null!"))
            return
        }
        repository.checkout(DataProvider.USER_ID, (amount * 100).toInt(), onResult)
    }

    fun cancelCheckout() {
        repository.cancel()
    }

    fun clearData() {
        cancelCheckout()
        _cartLiveData.value = null
        _paymentCardLiveData.value = null
    }
}