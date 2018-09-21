package com.training.tinhla.training.base.custom_view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.squareup.picasso.Picasso
import com.training.tinhla.training.base.Ulti
import com.training.tinhla.training.base.model.constant.ALIGNMENT
import com.training.tinhla.training.base.model.json.ColumnJSON

class NormalImageView : ImageView {
    lateinit var data: ColumnJSON
    var parentWidth : Int = 0

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs : AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)
    constructor(context: Context, data: ColumnJSON, parentWidth:Int) : super(context, null){
        this.data = data
        this.parentWidth = parentWidth

        var lpWidth:Int= getLayoutParamWidth(data.percentWidth)
        var lpHeight:Int = getLayoutParamHeight(data.height) // in dp

        var lp = LinearLayout.LayoutParams(lpWidth, lpHeight)

        when (data.alignment) {
            ALIGNMENT.LEFT.value -> {
                scaleType = ImageView.ScaleType.FIT_START
            }

            ALIGNMENT.CENTER.value -> scaleType = ImageView.ScaleType.CENTER

            ALIGNMENT.RIGHT.value -> scaleType = ImageView.ScaleType.FIT_END
        }
        layoutParams = lp

        Picasso.get().load(data.parameter?.url).into(this)
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