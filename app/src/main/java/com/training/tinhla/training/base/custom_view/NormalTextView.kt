package com.training.tinhla.training.base.custom_view

import android.content.Context
import android.graphics.Color
import android.support.v4.widget.TextViewCompat
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.training.tinhla.training.base.Ulti
import com.training.tinhla.training.base.model.constant.ALIGNMENT
import com.training.tinhla.training.base.model.json.ColumnJSON
import java.time.format.TextStyle

class NormalTextView : TextView {
    lateinit var data: ColumnJSON
    var parentWidth : Int = 0

    constructor(context:Context) : super(context)
    constructor(context: Context, attrs : AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)
    constructor(context: Context, data: ColumnJSON, parentWidth:Int) : super(context, null){
        this.data = data
        this.parentWidth = parentWidth

        setText(data.parameter?.text?:"")
        setTextColor(Color.parseColor(data.parameter?.fontColor?:"#ffffff"))

        when (data.alignment) {
            ALIGNMENT.LEFT.value -> {
                gravity = Gravity.LEFT
            }

            ALIGNMENT.CENTER.value -> gravity = Gravity.CENTER_HORIZONTAL

            ALIGNMENT.RIGHT.value -> gravity = Gravity.END
        }

        var lp = LinearLayout.LayoutParams(getLayoutParamWidth(data.percentWidth), getLayoutParamHeight(data.height))

        layoutParams = lp
    }

    fun getLayoutParamWidth(percentWidth:Int): Int {
        when (percentWidth) {
            100 -> {
                return LinearLayout.LayoutParams.MATCH_PARENT
            }

            0 -> {
                return LinearLayout.LayoutParams.WRAP_CONTENT
            }

            else -> return (percentWidth * parentWidth)
        }
    }

    fun getLayoutParamHeight(height:Int): Int {
        when (height) {
            0 -> return LinearLayout.LayoutParams.WRAP_CONTENT
            else -> return Ulti.dpToPx(context, height)
        }
    }

}