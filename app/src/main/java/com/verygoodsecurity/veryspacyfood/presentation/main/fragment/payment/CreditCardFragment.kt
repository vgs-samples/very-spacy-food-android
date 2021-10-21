package com.verygoodsecurity.veryspacyfood.presentation.main.fragment.payment

import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.verygoodsecurity.veryspacyfood.BuildConfig
import com.verygoodsecurity.veryspacyfood.R
import com.verygoodsecurity.veryspacyfood.presentation.main.MainNavigationHandler
import com.verygoodsecurity.veryspacyfood.presentation.main.fragment.loading.LoadingDialogFragment
import com.verygoodsecurity.veryspacyfood.presentation.main.model.SecureCard
import com.verygoodsecurity.veryspacyfood.presentation.main.viewmodel.MainViewModel
import com.verygoodsecurity.veryspacyfood.util.DataProvider.USER_ID
import com.verygoodsecurity.veryspacyfood.util.extension.expandTouchArea
import com.verygoodsecurity.veryspacyfood.util.extension.hideKeyboard
import com.verygoodsecurity.veryspacyfood.util.extension.showShort
import com.verygoodsecurity.vgscollect.core.Environment
import com.verygoodsecurity.vgscollect.core.HTTPMethod
import com.verygoodsecurity.vgscollect.core.VGSCollect
import com.verygoodsecurity.vgscollect.core.VgsCollectResponseListener
import com.verygoodsecurity.vgscollect.core.model.network.VGSRequest
import com.verygoodsecurity.vgscollect.core.model.network.VGSResponse
import com.verygoodsecurity.vgscollect.view.InputFieldView
import com.verygoodsecurity.vgscollect.view.card.CardType
import com.verygoodsecurity.vgscollect.view.card.icon.CardIconAdapter
import kotlinx.android.synthetic.main.fragment_credit_card.*
import kotlinx.android.synthetic.main.fragment_credit_card.view.*
import java.util.*

class CreditCardFragment : BottomSheetDialogFragment(), VgsCollectResponseListener {

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var navigation: MainNavigationHandler

    private var loadingDialog: DialogFragment? = null

    private val vgsCollect: VGSCollect by lazy {
        VGSCollect(requireContext(), BuildConfig.VAULT_ID, Environment.SANDBOX).apply {
            addOnResponseListeners(this@CreditCardFragment)
        }
    }

    override fun getTheme(): Int = R.style.CreditCardSheetDialogTheme

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigation = (context as? MainNavigationHandler)
            ?: throw IllegalStateException("Activity should implement MainNavigationHandler")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_credit_card, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        bindViews()
        initListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        hideLoading()
    }

    override fun onDestroy() {
        super.onDestroy()
        vgsCollect.onDestroy()
    }

    override fun onResponse(response: VGSResponse?) {
        hideLoading()
        when (response) {
            is VGSResponse.SuccessResponse -> handleSuccessfulResponse()
            is VGSResponse.ErrorResponse -> handleErrorResponse(response.localizeMessage)
        }
    }

    private fun initViews() {
        etCreditCardNumber.setCardIconAdapter(object : CardIconAdapter(requireContext()) {

            override fun getIcon(cardType: CardType, name: String?, resId: Int, r: Rect): Drawable {
                return when (cardType) {
                    CardType.VISA -> getDrawable(R.drawable.ic_visa_light)
                    else -> getDrawable(R.drawable.ic_card_white_31dp)
                }
            }
        })
        etCreditCardCVC?.expandTouchArea(R.dimen.margin_padding_size_medium)
    }

    private fun bindViews() {
        vgsCollect.bindView(etCreditCardHolderName)
        vgsCollect.bindView(etCreditCardNumber)
        vgsCollect.bindView(etCreditCardDate)
        vgsCollect.bindView(etCreditCardCVC)
    }

    private fun initListeners() {
        mtvCreditCardSave?.setOnClickListener {
            saveCard()
        }
        etCreditCardCVC.setOnEditorActionListener(object :
            InputFieldView.OnEditorActionListener {
            override fun onEditorAction(v: View?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    saveCard()
                    return true
                }
                return false
            }
        })
    }

    private fun saveCard() {
        validateFieldsAndRun {
            showLoading()
            view?.hideKeyboard()
            vgsCollect.asyncSubmit(
                VGSRequest.VGSRequestBuilder()
                    .setPath(REQUEST_PATH)
                    .setMethod(HTTPMethod.POST)
                    .setCustomData(mapOf(KET_REQUEST_DATA_USER_ID to USER_ID))
                    .build()
            )
        }
    }

    private fun validateFieldsAndRun(block: () -> Unit) {
        //TODO: Implement validation when main lib will be updated
        block.invoke()
    }

    private fun handleSuccessfulResponse() {
        requireContext().showShort(R.string.credit_card_successfully_added_msg)
        viewModel.addPaymentMethod(
            SecureCard(
                etCreditCardNumber?.getState()?.bin ?: "",
                etCreditCardNumber?.getState()?.last ?: "",
                etCreditCardNumber?.getState()?.cardBrand ?: ""
            )
        )
        dismiss()
    }

    private fun handleErrorResponse(message: String) {
        requireContext().showShort(message)
    }

    fun showLoading() {
        loadingDialog?.dismiss()
        loadingDialog = LoadingDialogFragment.newInstance(LoadingDialogFragment.Style.OVERLAY)
        loadingDialog?.show(childFragmentManager, null)
    }

    fun hideLoading() {
        loadingDialog?.dismiss()
    }

    companion object {

        const val TAG = "CreditCardFragment"

        private const val REQUEST_PATH = "/post"

        private const val KET_REQUEST_DATA_USER_ID = "userId"
    }
}