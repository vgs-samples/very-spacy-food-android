package com.verygoodsecurity.veryspacyfood.domain.repository

import com.verygoodsecurity.veryspacyfood.BuildConfig
import com.verygoodsecurity.veryspacyfood.domain.Result
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException
import java.util.concurrent.TimeUnit

class CheckoutRepository {

    private val client: OkHttpClient = OkHttpClient.Builder()
        .callTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        .build()

    fun checkout(userId: String, amount: Double, onResult: (Result) -> Unit): Call {
        return client.newCall(buildCheckoutRequest(userId, amount)).also {
            it.enqueue(object : Callback {

                override fun onFailure(call: Call, e: IOException) {
                    onResult.invoke(Result.Error(e.localizedMessage))
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        onResult.invoke(Result.Success)
                    } else {
                        onResult.invoke(Result.Error("Code = ${response.code}, message = ${response.message}"))
                    }
                }
            })
        }
    }

    fun cancel() {
        client.dispatcher.cancelAll()
    }

    private fun buildCheckoutRequest(userId: String, amount: Double): Request {
        return Request.Builder()
            .url(BuildConfig.BASE_URL + CHECKOUT_PATH)
            .post(JSONObject().apply {
                put(USER_ID_JSON_SELECTOR, userId)
                put(AMOUNT_JSON_SELECTOR, (amount * 100).toInt())
            }.toString().toRequestBody(CONTENT_TYPE))
            .build()
    }

    companion object {

        private const val DEFAULT_TIMEOUT: Long = 10

        private const val CHECKOUT_PATH = "/payments/stripe"

        private const val USER_ID_JSON_SELECTOR = "userId"
        private const val AMOUNT_JSON_SELECTOR = "amount"

        private val CONTENT_TYPE = "application/json".toMediaType()
    }
}