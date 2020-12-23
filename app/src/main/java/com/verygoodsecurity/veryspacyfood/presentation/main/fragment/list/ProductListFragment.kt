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
import com.verygoodsecurity.veryspacyfood.util.extension.showShort
import kotlinx.android.synthetic.main.fragment_product_list.*
import kotlinx.android.synthetic.main.main_toolbar.*

@Suppress("unused")
class ProductListFragment : BaseMainFragment(R.layout.fragment_product_list),
    ProductsAdapter.OnProductClickListener {

    @Suppress("unused")
    private val viewModel: MainViewModel by activityViewModels()

    private val adapter = ProductsAdapter(this)

    override fun initView(savedInstanceState: Bundle?) {
        subscribeCartUpdate()
        initListView()
        initListeners()
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

    private fun subscribeCartUpdate() {
        viewModel.cartLiveData.observe(viewLifecycleOwner, {
            tvMainToolbarBasketCounter?.text = if (it.isNullOrEmpty()) null else it.count().toString()
        })
        viewModel.paymentCardLiveData.observe(viewLifecycleOwner, {
            mbProductListPayment?.text = getString(
                if (it == null) {
                    R.string.product_list_add_payment_method_btn_title
                } else {
                    R.string.product_list_checkout_btn_title
                }
            )
        })
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

    private fun initListeners() {
        mbProductListPayment?.setOnClickListener {
            if (viewModel.paymentCardLiveData.value == null) {
                navigation.navigateToAddCreditCard()
            } else {
                handleCheckoutClicked()
            }
        }
    }

    private fun handleCheckoutClicked() {
        if (viewModel.cartLiveData.value.isNullOrEmpty()) {
            requireContext().showShort(R.string.product_list_checkout_error_msg)
            return
        }
        navigation.navigateToCheckout()
    }
}