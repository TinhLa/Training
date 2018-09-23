package com.training.tinhla.training.base.custom_view

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.training.tinhla.training.base.model.json.ButtonModel

class TemplateButtonsLine : RelativeLayout {
    lateinit var data: ArrayList<ButtonModel>

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs : AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)
    constructor(context: Context, data: ArrayList<ButtonModel>) : super(context, null){
        this.data = data

        createViews()
    }

    private fun createViews() {
        var cancelBtn = TemplateButton(context, data.get(0))
        var cancelBtnLP = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
        cancelBtnLP.addRule(RelativeLayout.ALIGN_PARENT_LEFT)
        cancelBtn.layoutParams = cancelBtnLP

        var submitBtn = TemplateButton(context, data.get(1))
        var submitBtnLP = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
        submitBtnLP.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
        submitBtn.layoutParams = submitBtnLP

        addView(cancelBtn)
        addView(submitBtn)
    }
}