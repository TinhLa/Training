package com.training.tinhla.training.base.custom_view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import com.training.tinhla.training.base.ViewUlti
import com.training.tinhla.training.base.model.constant.ALIGNMENT
import com.training.tinhla.training.base.model.json.ColumnModel

class NormalTextView : TextView {
    lateinit var data: ColumnModel
    var parentWidth : Int = 0

    constructor(context:Context) : super(context)
    constructor(context: Context, attrs : AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)
    constructor(context: Context, data: ColumnModel, parentWidth:Int) : super(context, null){
        this.data = data
        this.parentWidth = parentWidth

        maxWidth = (parentWidth * 0.9f).toInt()

        setText(data.parameter?.text?:"")
        setTextColor(Color.parseColor(data.parameter?.fontColor?:"#ffffff"))

        when (data.alignment) {
            ALIGNMENT.LEFT.value -> {
                gravity = Gravity.LEFT
            }

            ALIGNMENT.CENTER.value -> gravity = Gravity.CENTER_HORIZONTAL

            ALIGNMENT.RIGHT.value -> gravity = Gravity.END
        }

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var width = MeasureSpec.getSize(widthMeasureSpec)

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}