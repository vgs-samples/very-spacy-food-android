package com.verygoodsecurity.veryspacyfood.domain.repository

import com.verygoodsecurity.veryspacyfood.data.dto.Transaction
import com.verygoodsecurity.veryspacyfood.data.source.remote.CheckoutDataSource
import com.verygoodsecurity.veryspacyfood.domain.Result

class CheckoutRepository {

    private val remoteDataSource: CheckoutDataSource = CheckoutDataSource()

    fun checkout(userId: String, amount: Int, onResult: (Result) -> Unit) {
        remoteDataSource.checkout(Transaction(userId, amount), onResult)
    }

    fun cancel() {
        remoteDataSource.cancel()
    }
}