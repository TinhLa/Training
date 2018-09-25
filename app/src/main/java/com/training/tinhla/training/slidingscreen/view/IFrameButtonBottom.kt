package com.training.tinhla.training.slidingscreen.view

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.training.tinhla.training.R
import com.training.tinhla.training.commonmodel.templatebutton.NewTemplate
import com.training.tinhla.training.commonmodel.templatebutton.TemplateButton

class IFrameButtonBottom @JvmOverloads constructor(context: Context, att: AttributeSet? = null, def: Int = 0) : LinearLayout(context, att, def) {
    fun onCreateView(templateButton: TemplateButton) {
        when (templateButton.labelSet) {
            "pay" -> addButton(templateButton.new)
        }
    }

    private fun addButton(news: List<NewTemplate>) {

        for (i in 0 until news.size) {
            val metrics = this.context.resources.displayMetrics
            val paramsFrame = LinearLayout.LayoutParams(metrics.widthPixels / news.size, LinearLayout.LayoutParams.MATCH_PARENT)
            paramsFrame.setMargins(0, 0, 0, 0)
            val lineFrame = LinearLayout(this.context)
            lineFrame.layoutParams = paramsFrame
            lineFrame.orientation = LinearLayout.HORIZONTAL

            val paramImg = LinearLayout.LayoutParams(60, 60)
            val icon = ImageView(this.context)
            paramImg.gravity = Gravity.CENTER_VERTICAL
            when(news[i].action){
                "cancel" -> Glide.with(this.context).load(this.context.resources.getIdentifier("btn_cancel","drawable",this.context.packageName)).into(icon)
                "submit" -> Glide.with(this.context).load(this.context.resources.getIdentifier("btn_confirm","drawable",this.context.packageName)).into(icon)
            }

            icon.layoutParams = paramImg
            lineFrame.addView(icon)

            val paramText = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            val text = TextView(this.context)
            paramText.gravity = Gravity.CENTER_VERTICAL
            paramText.setMargins(10, 10, 10, 10)
            text.layoutParams = paramText
            text.text = news[i].textKey
            lineFrame.addView(text)
            if (news[i].state == "enable")
                this.addView(lineFrame)
        }

    }
}