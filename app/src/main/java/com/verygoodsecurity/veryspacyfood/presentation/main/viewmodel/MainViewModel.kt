package com.verygoodsecurity.veryspacyfood.presentation.main.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.verygoodsecurity.veryspacyfood.domain.Result
import com.verygoodsecurity.veryspacyfood.domain.repository.CheckoutRepository
import com.verygoodsecurity.veryspacyfood.presentation.main.model.Product
import com.verygoodsecurity.veryspacyfood.presentation.main.model.SecureCard
import com.verygoodsecurity.veryspacyfood.util.DataProvider
import com.verygoodsecurity.veryspacyfood.util.extension.isNetworkConnectionAvailable
import okhttp3.Call

class MainViewModel constructor(private val app: Application) : AndroidViewModel(app) {

    private val repository = CheckoutRepository()

    private val _cartLiveData = MutableLiveData<ArrayList<Product>>(ArrayList())
    val cartLiveData: LiveData<ArrayList<Product>> get() = _cartLiveData

    private val _paymentCardLiveData = MutableLiveData<SecureCard>()
    val paymentCardLiveData: LiveData<SecureCard> get() = _paymentCardLiveData

    private var checkoutCall: Call? = null

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
        if (!app.applicationContext.isNetworkConnectionAvailable()) {
            onResult.invoke(Result.Error("No internet connection!"))
            return
        }
        val amount = cartLiveData.value?.sumByDouble { it.price }
        if (amount == null) {
            onResult.invoke(Result.Error("Amount is null!"))
            return
        }
        checkoutCall?.cancel()
        checkoutCall = repository.checkout(DataProvider.USER_ID, amount, onResult)
    }

    fun cancelCheckout() {
        checkoutCall?.cancel()
    }

    fun clearData() {
        cancelCheckout()
        _cartLiveData.value = null
        _paymentCardLiveData.value = null
    }
}