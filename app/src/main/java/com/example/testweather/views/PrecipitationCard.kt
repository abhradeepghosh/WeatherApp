package com.example.testweather.views

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.res.getColorOrThrow
import androidx.core.content.res.getDrawableOrThrow
import com.example.testweather.R
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.precipitation_card_view.view.*


class PrecipitationCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.precipitation_card_view, this)

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.PrecipitationCard)
        val titleText = typedArray.getText(R.styleable.PrecipitationCard_prepTitle)
        val unitText = typedArray.getText(R.styleable.PrecipitationCard_prepUnit)
        val valueText = typedArray.getText(R.styleable.PrecipitationCard_prepValue)
        val resId = typedArray.getDrawableOrThrow(R.styleable.PrecipitationCard_prepIcon)
        val textColor = typedArray.getColorOrThrow(R.styleable.PrecipitationCard_prepTextColor)

        title.text = titleText
        unit.text = unitText
        value.text = valueText
        title.setTextColor(textColor)
        unit.setTextColor(textColor)
        value.setTextColor(textColor)
        icon.setImageDrawable(resId)
        icon.setColorFilter(textColor)
        typedArray.recycle()
    }
}