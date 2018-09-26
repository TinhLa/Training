package com.training.tinhla.training.base.custom_view

import android.content.Context
import android.graphics.Color
import android.os.Build
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
        minWidth = (parentWidth * 0.1f).toInt()

        setText(data.parameter?.text?:"")
        setTextColor(Color.parseColor(data.parameter?.fontColor?:"#ffffff"))

        setAlign()
    }

    private fun setAlign() {
        var hAlign = Gravity.LEFT
        var vAlign = Gravity.CENTER_VERTICAL

        when (data.alignment) {
            ALIGNMENT.CENTER.value -> hAlign = Gravity.CENTER_HORIZONTAL

            ALIGNMENT.RIGHT.value -> hAlign = if(android.os.Build.VERSION.SDK_INT >= 17 ) Gravity.END else Gravity.RIGHT

            else -> {
                hAlign = if(android.os.Build.VERSION.SDK_INT >= 17 ) Gravity.START else Gravity.LEFT
            }
        }

        when (data.verticalAlignment) {
            ALIGNMENT.CENTER.value -> vAlign = Gravity.CENTER_VERTICAL

            ALIGNMENT.TOP.value -> vAlign = Gravity.TOP

            else -> {
                vAlign = Gravity.BOTTOM
            }
        }

        gravity = hAlign or vAlign
    }
}