package com.verygoodsecurity.veryspacyfood.presentation.main.fragment.loading

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import com.verygoodsecurity.veryspacyfood.R
import kotlinx.android.synthetic.main.fragment_loading_filled.*

class LoadingDialogFragment : DialogFragment() {

    private val style: Style by lazy { arguments?.get(KEY_BUNDLE_LOADING_DIALOG_STYLE) as Style }

    private var listener: LoadingDialogClickListener? = null

    override fun getTheme(): Int = when (style) {
        Style.FILLED -> R.style.LoadingDialog_Filled
        Style.OVERLAY -> R.style.LoadingDialog_Overlay
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = parentFragment as? LoadingDialogClickListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(getLayoutId(), container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (style == Style.FILLED) {
            initAnimation()
            initListeners()
        }
    }

    @LayoutRes
    private fun getLayoutId() = when (style) {
        Style.FILLED -> R.layout.fragment_loading_filled
        Style.OVERLAY -> R.layout.fragment_loading_overlay
    }

    private fun initAnimation() {
        ivLoadingFilledImage.setBackgroundResource(R.drawable.saturn_loading_animation_drawable)
        (ivLoadingFilledImage.background as AnimationDrawable).start()
    }

    private fun initListeners() {
        ivCheckoutCompleteClose?.setOnClickListener {
            listener?.onCancelClick()
        }
    }

    interface LoadingDialogClickListener {

        fun onCancelClick()
    }

    enum class Style {

        FILLED,
        OVERLAY
    }

    companion object {

        private const val KEY_BUNDLE_LOADING_DIALOG_STYLE = "key_bundle_loading_dialog_style"

        fun newInstance(style: Style) = LoadingDialogFragment().apply {
            arguments = Bundle().apply {
                putSerializable(KEY_BUNDLE_LOADING_DIALOG_STYLE, style)
            }
        }
    }
}