package com.verygoodsecurity.veryspacyfood.presentation.main.fragment.list

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import com.verygoodsecurity.veryspacyfood.R
import com.verygoodsecurity.veryspacyfood.presentation.core.adapter.PaddingItemDecoration
import com.verygoodsecurity.veryspacyfood.presentation.main.fragment.core.BaseMainFragment
import com.verygoodsecurity.veryspacyfood.presentation.main.fragment.list.adapter.ProductsAdapter
import com.verygoodsecurity.veryspacyfood.presentation.main.model.Product
import com.verygoodsecurity.veryspacyfood.presentation.main.viewmodel.MainViewModel
import com.verygoodsecurity.veryspacyfood.util.DataProvider.TEST_DATA
import kotlinx.android.synthetic.main.fragment_product_list.*
import kotlinx.android.synthetic.main.main_toolbar.*

@Suppress("unused")
class ProductListFragment : BaseMainFragment(R.layout.fragment_product_list),
    ProductsAdapter.OnProductClickListener {

    @Suppress("unused")
    private val viewModel: MainViewModel by activityViewModels()

    private val adapter = ProductsAdapter(this)

    override fun initView(savedInstanceState: Bundle?) {
        initListView()
        subscribeCartUpdate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        rvProductList?.adapter = null
    }

    override fun onProductClick(product: Product) {
        navigation.navigateToDetails(product)
    }

    override fun onAddClick(product: Product) {
        viewModel.addToCart(product)
    }

    private fun initListView() {
        rvProductList?.addItemDecoration(
            PaddingItemDecoration(
                resources.getDimensionPixelOffset(R.dimen.margin_padding_size_large),
                0,
                resources.getDimensionPixelOffset(R.dimen.margin_padding_size_xxxlarge),
                resources.getDimensionPixelOffset(R.dimen.margin_padding_size_large)
            )
        )
        rvProductList?.adapter = adapter.apply {
            submitList(TEST_DATA)
        }
    }

    private fun subscribeCartUpdate() {
        viewModel.cartLiveData.observe(viewLifecycleOwner, {
            tvMainToolbarBasketCounter?.text = it.count().toString()
        })
    }
}