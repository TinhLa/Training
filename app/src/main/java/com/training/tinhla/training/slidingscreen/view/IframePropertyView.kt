package com.training.tinhla.training.slidingscreen.view

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.training.tinhla.training.R
import com.training.tinhla.training.commonmodel.templatebody.iframeProperty.IframeLineTemplate

class IframePropertyView @JvmOverloads constructor(context: Context, att: AttributeSet? = null, def: Int = 0) : LinearLayout(context, att, def) {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun createItem(dataIframeProperty: List<IframeLineTemplate>) {
        for (i in 0 until dataIframeProperty.size) {
            when (dataIframeProperty[i].columns[0].contentType) {
                "image" -> addImage(dataIframeProperty[i].columns[0].alignment)
                "text" -> addText(dataIframeProperty[i].columns[0].parameter.text, dataIframeProperty[i].columns[0].percentWidth,
                        dataIframeProperty[i].columns[0].parameter.fontColor,dataIframeProperty[i].columns[0].alignment)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun addImage(alignment: String) {
        val image = ImageView(this.context)
        image.background = this.context.getDrawable(R.drawable.ic_baseline_favorite_border_24px)
        val params = LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.WRAP_CONTENT, android.widget.LinearLayout.LayoutParams.WRAP_CONTENT)
        when (alignment) {
            "center" -> params.gravity = Gravity.CENTER_HORIZONTAL
            "left" -> params.gravity = Gravity.START
            "right" -> params.gravity = Gravity.END
        }
        params.setMargins(10, 10, 10, 10)
        image.layoutParams = params
        this.addView(image)
    }

    private fun addText(label: String, percentWidth: Int, fontColor: String, alignment: String) {
        val textView = TextView(this.context)
        val params = LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.WRAP_CONTENT, android.widget.LinearLayout.LayoutParams.WRAP_CONTENT)
        params.setMargins(percentWidth, 20, percentWidth, 20)
        when (alignment) {
            "center" -> params.gravity = Gravity.CENTER_HORIZONTAL
            "left" -> params.gravity = Gravity.START
            "right" -> params.gravity = Gravity.END
        }
        textView.layoutParams = params
        textView.setTextColor(Color.parseColor(fontColor))
        textView.text = label
        this.addView(textView)
    }
}