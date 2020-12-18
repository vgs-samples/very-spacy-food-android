package com.verygoodsecurity.veryspacyfood.presentation.main.fragment.payment

import android.annotation.SuppressLint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.verygoodsecurity.veryspacyfood.BuildConfig
import com.verygoodsecurity.veryspacyfood.R
import com.verygoodsecurity.veryspacyfood.presentation.main.viewmodel.MainViewModel
import com.verygoodsecurity.veryspacyfood.util.DataProvider.USER_ID
import com.verygoodsecurity.veryspacyfood.util.extension.expandTouchArea
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

    private val vgsCollect: VGSCollect by lazy {
        VGSCollect(requireContext(), BuildConfig.TENANT_ID, Environment.SANDBOX).apply {
            addOnResponseListeners(this@CreditCardFragment)
        }
    }

    override fun getTheme(): Int = R.style.CreditCardSheetDialogTheme

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

    override fun onResponse(response: VGSResponse?) {
        when (response) {
            is VGSResponse.SuccessResponse -> handleSuccessfulResponse(response)
            is VGSResponse.ErrorResponse -> handleErrorResponse(response)
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

    @SuppressLint("NewApi")
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

    private fun handleSuccessfulResponse(response: VGSResponse.SuccessResponse) {
        // TODO: Handle response
    }

    private fun handleErrorResponse(response: VGSResponse.ErrorResponse) {
        Toast.makeText(requireContext(), response.localizeMessage, Toast.LENGTH_SHORT).show()
    }

    companion object {

        private const val REQUEST_PATH = "/cards/new"

        private const val KET_REQUEST_DATA_USER_ID = "userId"
    }
}