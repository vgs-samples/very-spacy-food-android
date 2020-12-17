package com.verygoodsecurity.veryspacyfood.presentation.main.fragment.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.verygoodsecurity.veryspacyfood.R
import com.verygoodsecurity.veryspacyfood.presentation.main.model.Product
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_product.view.*

class ProductsAdapter constructor(
    private val listener: OnProductClickListener
) : ListAdapter<Product, ProductsAdapter.ProductViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ProductViewHolder constructor(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        init {

            containerView.setOnClickListener {
                listener.onProductClick(getItem(adapterPosition))
            }

            containerView.mbItemProductAdd?.setOnClickListener {
                listener.onAddClick(getItem(adapterPosition))
            }
        }

        fun bind(item: Product) {
            containerView.ivItemProductImage?.setImageResource(item.image)
            containerView.tvItemProductTitle?.text = item.title
            containerView.tvItemProductDescription?.text = item.description
            containerView.tvItemProductPrice?.text =
                containerView.resources.getString(R.string.all_price_mask, item.price)
        }
    }

    interface OnProductClickListener {

        fun onProductClick(product: Product)

        fun onAddClick(product: Product)
    }

    companion object {

        private val DIFF_UTIL = object : DiffUtil.ItemCallback<Product>() {

            override fun areItemsTheSame(oldItem: Product, newItem: Product) = oldItem == newItem

            override fun areContentsTheSame(oldItem: Product, newItem: Product) = oldItem == newItem
        }
    }
}