package com.training.tinhla.training.base.custom_view

import android.content.Context
import android.graphics.Color
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.ImageView
import android.widget.TextView
import com.training.tinhla.training.R
import com.training.tinhla.training.base.ViewIdGenerator
import com.training.tinhla.training.base.model.json.ButtonModel

class TemplateButton : ConstraintLayout {
    lateinit var data: ButtonModel

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs : AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)
    constructor(context: Context, data: ButtonModel) : super(context, null){
        this.data = data

        createViews()
    }

    private fun createViews() {
        when (data.action) {
            "cancel" -> {
                createCancelButton()
            }

            "submit" -> {
                createSubmitButton()
            }
        }

        when (data.state) {
            "enable" -> {
                isEnabled = true
            }

            else -> {
                isEnabled = false
            }
        }
    }

    private fun createSubmitButton() {

        var wrapContent = ConstraintLayout.LayoutParams.WRAP_CONTENT

        var label = TextView(context)
        label.id = ViewIdGenerator.generate()
        label.layoutParams = ConstraintLayout.LayoutParams(wrapContent, wrapContent)
        label.setText(data.textKey)
        label.setTextColor(Color.BLACK)
        addView(label)

        var img = ImageView(context)
        img.id = ViewIdGenerator.generate()
        img.layoutParams = ConstraintLayout.LayoutParams(wrapContent, wrapContent)
        img.setImageResource(R.drawable.bg_btn_submit)
        addView(img)

        var set = ConstraintSet()
        set.clone(this)

        set.connect(label.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
        set.connect(label.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
        set.connect(label.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)

        set.connect(img.id, ConstraintSet.START, label.id, ConstraintSet.END)
        set.connect(img.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
        set.connect(img.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)

        set.applyTo(this)
    }

    private fun createCancelButton() {

        var wrapContent = ConstraintLayout.LayoutParams.WRAP_CONTENT

        var img = ImageView(context)
        img.id = ViewIdGenerator.generate()
        img.layoutParams = ConstraintLayout.LayoutParams(wrapContent, wrapContent)
        img.setImageResource(R.drawable.bg_btn_cancel)
        addView(img)

        var label = TextView(context)
        label.id = ViewIdGenerator.generate()
        label.layoutParams = ConstraintLayout.LayoutParams(wrapContent, wrapContent)
        label.setText(data.textKey)
        label.setTextColor(Color.BLACK)
        addView(label)

        var set = ConstraintSet()
        set.clone(this)

        var parentID = ConstraintSet.PARENT_ID
        var margin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2f, resources.displayMetrics).toInt()

        set.connect(img.id, ConstraintSet.START, parentID, ConstraintSet.START)
        set.connect(img.id, ConstraintSet.TOP, parentID, ConstraintSet.TOP)
        set.connect(img.id, ConstraintSet.BOTTOM, parentID, ConstraintSet.BOTTOM)

        set.connect(label.id, ConstraintSet.START, img.id, ConstraintSet.END, margin)
        set.connect(label.id, ConstraintSet.TOP, parentID, ConstraintSet.TOP)
        set.connect(label.id, ConstraintSet.BOTTOM, parentID, ConstraintSet.BOTTOM)

        set.applyTo(this)
    }
}