package com.verygoodsecurity.veryspacyfood.presentation.main.fragment.details

import android.os.Bundle
import androidx.core.view.ViewCompat
import androidx.fragment.app.activityViewModels
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import com.verygoodsecurity.veryspacyfood.R
import com.verygoodsecurity.veryspacyfood.presentation.main.fragment.core.BaseMainFragment
import com.verygoodsecurity.veryspacyfood.presentation.main.model.Product
import com.verygoodsecurity.veryspacyfood.presentation.main.viewmodel.MainViewModel
import com.verygoodsecurity.veryspacyfood.util.extension.getColorStateListSafe
import com.verygoodsecurity.veryspacyfood.util.extension.show
import kotlinx.android.synthetic.main.fragment_product_details.*
import kotlinx.android.synthetic.main.main_toolbar.*

class ProductDetailsFragment : BaseMainFragment(R.layout.fragment_product_details) {

    @Suppress("unused")
    private val viewModel: MainViewModel by activityViewModels()

    private val product: Product by lazy {
        requireArguments().get(PRODUCT_KEY) as Product
    }

    override fun initView(savedInstanceState: Bundle?) {
        initColorfulBackground()
        initToolbar()
        initProductDescription()
        initListeners()
    }

    private fun initColorfulBackground() {
        val radius = resources.getDimension(R.dimen.product_details_corner_radius)
        simProductDetailsBackground.background = MaterialShapeDrawable(
            ShapeAppearanceModel().toBuilder()
                .setBottomLeftCorner(CornerFamily.ROUNDED, radius)
                .build()
        ).apply { fillColor = requireContext().getColorStateListSafe(product.associatedColor) }
    }

    private fun initToolbar() {
        mtvMainToolbarBack?.show()
        mtvMainToolbarBack?.setOnClickListener {
            navigation.navigateBack()
        }
    }

    private fun initProductDescription() {
        ViewCompat.requestApplyInsets(clProductDetailsContentContainer)
        ivProductDetailsImage?.setImageResource(product.image)
        mtvProductDetailsTitle?.text =
            getString(R.string.product_details_title_mask, product.title, product.weight)
        mtvProductDetailsDescription?.text = product.description
        mtvProductDetailsPrice?.text = getString(R.string.all_price_mask, product.price)
    }

    private fun initListeners() {
        viewModel.cartLiveData.observe(viewLifecycleOwner, {
            tvMainToolbarBasketCounter?.text = if (it.isNullOrEmpty()) null else it.count().toString()
        })
        mbProductDetailsAddToCart?.setOnClickListener {
            viewModel.addToCart(product)
        }
    }

    companion object {

        const val TAG = "ProductDetailsFragment"

        private const val PRODUCT_KEY = "product"

        fun newInstance(product: Product) = ProductDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(PRODUCT_KEY, product)
            }
        }
    }
}