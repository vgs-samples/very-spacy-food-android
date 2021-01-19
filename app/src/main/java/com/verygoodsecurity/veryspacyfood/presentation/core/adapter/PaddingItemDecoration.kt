package com.verygoodsecurity.veryspacyfood.presentation.core.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class PaddingItemDecoration constructor(
    private val start: Int,
    private val top: Int,
    private val bottom: Int,
    private val end: Int
) : ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.left = start
        outRect.right = end
        outRect.bottom = bottom

        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = top
        }
    }
}