package com.velvet.ui

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.velvet.ui.databinding.FragmentBonusBinding

class BonusFragment : Fragment() {

    private var binding: FragmentBonusBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBonusBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Setup colors from Bundle
        val colorStateList = ColorStateList.valueOf(requireArguments().getInt(ACCENT_COLOR_KEY))
        val primaryFontSize = requireArguments().getFloat(PRIMARY_FONT_SIZE_KEY)
        val secondaryFontSize = requireArguments().getFloat(SECONDARY_FONT_SIZE_KEY)
        binding!!.goButton.imageTintList = colorStateList
        binding!!.coloredBox.backgroundTintList = colorStateList
        binding!!.infoButton.imageTintList = colorStateList
        binding!!.bonusCounter.textSize = primaryFontSize
        binding!!.bonusesCurrent.textSize = primaryFontSize
        binding!!.willExpire.textSize = secondaryFontSize
        binding!!.bonusesExpiration.textSize = secondaryFontSize
        binding!!.expirationCounter.textSize = secondaryFontSize
        binding!!.expirationDate.textSize = secondaryFontSize
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun setExpirationDate(date: String) {
        binding!!.expirationDate.text = date
    }

    fun setBonusQuantity(quantity: String) {
        binding!!.bonusCounter.text = quantity
    }

    fun setExpirationQuantity(quantity: String) {
        binding!!.expirationCounter.text = quantity
    }

    companion object {
        private const val ACCENT_COLOR_KEY = "Accent color"
        private const val PRIMARY_FONT_SIZE_KEY = "Primary font size"
        private const val SECONDARY_FONT_SIZE_KEY = "Secondary font size"

        fun newInstance(primaryFontSize: Float, secondaryFontSize: Float, accentColor: Int): BonusFragment {
            val args = Bundle()
            args.putInt(ACCENT_COLOR_KEY, accentColor)
            args.putFloat(PRIMARY_FONT_SIZE_KEY, primaryFontSize)
            args.putFloat(SECONDARY_FONT_SIZE_KEY, secondaryFontSize)
            val fragment = BonusFragment()
            fragment.arguments = args
            return fragment
        }
    }
}