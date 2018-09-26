package com.training.tinhla.training.base.custom_view

import android.content.Context
import android.graphics.Color
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.training.tinhla.training.R
import com.training.tinhla.training.base.ViewIdGenerator
import com.training.tinhla.training.base.model.json.ButtonModel

class TemplateButton : ConstraintLayout {
    lateinit var data: ButtonModel

    val ICON_SIZE = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20f, resources.displayMetrics).toInt()
    val SPACE = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2f, resources.displayMetrics).toInt()

    companion object {
        val WRAP_CONTENT = ConstraintLayout.LayoutParams.WRAP_CONTENT
    }

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

        val label = createLabel()
        addView(label)

        val img = createIcon(R.drawable.btn_confirm)
        addView(img)

        setConstrains(label, img)
    }

    private fun createCancelButton() {

        val img = createIcon(R.drawable.btn_cancel)
        addView(img)

        val label = createLabel()
        addView(label)

        setConstrains(img, label)
    }

    private fun setConstrains(firstView: View, secondView: View) {
        val set = ConstraintSet()
        set.clone(this)

        set.connect(firstView.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
        set.connect(firstView.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
        set.connect(firstView.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)

        set.connect(secondView.id, ConstraintSet.START, firstView.id, ConstraintSet.END, SPACE)
        set.connect(secondView.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
        set.connect(secondView.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)

        set.applyTo(this)
    }

    private fun createLabel() : TextView{
        val label = TextView(context)
        label.id = ViewIdGenerator.generate()
        label.layoutParams = ConstraintLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        label.setText(data.textKey)
        label.setTextColor(Color.BLACK)
        return label
    }

    private fun createIcon(defaultDrawable:Int): ImageView {
        val img = ImageView(context)
        img.id = ViewIdGenerator.generate()
        img.layoutParams = ConstraintLayout.LayoutParams(ICON_SIZE, ICON_SIZE)
        img.setImageResource(defaultDrawable)

        return img
    }
}