package com.verygoodsecurity.veryspacyfood.presentation.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.verygoodsecurity.veryspacyfood.domain.Result
import com.verygoodsecurity.veryspacyfood.domain.repository.CheckoutRepository
import com.verygoodsecurity.veryspacyfood.presentation.main.model.Product
import com.verygoodsecurity.veryspacyfood.presentation.main.model.SecureCard
import com.verygoodsecurity.veryspacyfood.util.DataProvider

class MainViewModel : ViewModel() {

    private val repository = CheckoutRepository()

    private val _cartLiveData = MutableLiveData<ArrayList<Product>>(ArrayList())
    val cartLiveData: LiveData<ArrayList<Product>> get() = _cartLiveData

    private val _paymentCardLiveData = MutableLiveData<SecureCard>()
    val paymentCardLiveData: LiveData<SecureCard> get() = _paymentCardLiveData

    override fun onCleared() {
        super.onCleared()
        repository.cancel()
    }

    fun addToCart(product: Product) {
        _cartLiveData.value?.add(product)
        _cartLiveData.value = _cartLiveData.value
    }

    fun addPaymentMethod(card: SecureCard?) {
        _paymentCardLiveData.value = card
    }

    fun checkout(onResult: (Result) -> Unit) {
        val amount = cartLiveData.value?.sumByDouble { it.price }
        if (amount == null) {
            onResult.invoke(Result.Error())
            return
        }
        repository.checkout(DataProvider.USER_ID, amount, onResult)
    }
}