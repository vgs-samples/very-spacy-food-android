package com.verygoodsecurity.veryspacyfood.util.extension

import org.json.JSONObject

fun String.toJson(): JSONObject? = try {
    JSONObject(this)
} catch (e: Exception) {
    null
}