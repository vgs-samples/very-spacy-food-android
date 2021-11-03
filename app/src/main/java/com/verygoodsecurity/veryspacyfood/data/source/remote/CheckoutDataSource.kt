package com.verygoodsecurity.veryspacyfood.data.source.remote

import com.verygoodsecurity.veryspacyfood.BuildConfig
import com.verygoodsecurity.veryspacyfood.data.RetrofitProvider
import com.verygoodsecurity.veryspacyfood.data.dto.Transaction
import com.verygoodsecurity.veryspacyfood.domain.Result
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

// TODO: Remove cancel logic when add coroutines
class CheckoutDataSource {

    private val api: API = RetrofitProvider.provide(BuildConfig.BASE_URL).create(API::class.java)

    private var call: Call<ResponseBody>? = null

    fun checkout(transaction: Transaction, onResult: (Result) -> Unit) {
        call?.cancel()
        api.checkout(transaction).also { call = it }.enqueue(object : Callback<ResponseBody> {

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    onResult.invoke(Result.Success)
                } else {
                    onResult.invoke(Result.Error("Code = ${response.code()}, message = ${response.message()}"))
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                onResult.invoke(Result.Error(t.localizedMessage))
            }
        })
    }

    fun cancel() {
        call?.cancel()
    }

    interface API {

        @POST("/payments/stripe")
        fun checkout(@Body transaction: Transaction): Call<ResponseBody>
    }
}