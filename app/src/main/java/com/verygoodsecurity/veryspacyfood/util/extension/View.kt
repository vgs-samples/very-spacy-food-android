package com.verygoodsecurity.veryspacyfood.util.extension

import android.app.Activity
import android.graphics.Rect
import android.view.TouchDelegate
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.DimenRes


fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.hideKeyboard() {
    val imm: InputMethodManager =
        context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun View.expandTouchArea(@DimenRes expandedValueRes: Int) {
    (this.parent as? View)?.let { parent ->
        val expandedValue = this.resources.getDimensionPixelSize(expandedValueRes)
        parent.post {
            val rect = Rect()
            this@expandTouchArea.isEnabled = true
            this@expandTouchArea.getHitRect(rect)
            rect.top -= expandedValue
            rect.left -= expandedValue
            rect.right += expandedValue
            rect.bottom += expandedValue
            parent.touchDelegate = TouchDelegate(rect, this@expandTouchArea)
        }
    }
}