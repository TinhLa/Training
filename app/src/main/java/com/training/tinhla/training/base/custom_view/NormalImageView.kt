package com.training.tinhla.training.base.custom_view

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.training.tinhla.training.R
import com.training.tinhla.training.base.Ulti
import com.training.tinhla.training.base.ViewUlti
import com.training.tinhla.training.base.model.constant.ALIGNMENT
import com.training.tinhla.training.base.model.json.ColumnModel

class NormalImageView : ImageView {
    lateinit var data: ColumnModel
    var parentWidth : Int = 0

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs : AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    constructor(context: Context, data: ColumnModel, parentWidth:Int) : super(context, null){
        this.data = data
        this.parentWidth = parentWidth

        maxWidth = (parentWidth * 0.9f).toInt()
        minimumWidth = (parentWidth * 0.1f).toInt()

        val widthLP = getLayoutParamWidth(data.percentWidth)
        val lp = LinearLayout.LayoutParams(widthLP, getLayoutParamHeight(data.height))
        if (widthLP == 0) {
            lp.weight = data.percentWidth / 100f
        }

        when (data.alignment) {
            ALIGNMENT.LEFT.value ->
                scaleType = ImageView.ScaleType.FIT_START

            ALIGNMENT.RIGHT.value ->
                scaleType = ImageView.ScaleType.FIT_END

            ALIGNMENT.CENTER.value ->
                scaleType = ImageView.ScaleType.CENTER
        }

        if (!Ulti.isEmptyStr(data.parameter?.url)) {
            Glide.with(this).load(data.parameter?.url).into(this)
        }else{
            Glide.with(this).load(R.drawable.ic_iframe).into(this)
        }
    }

    fun getLayoutParamWidth(percentWidth:Int): Int {
        when (percentWidth) {

            0 -> {
                return LinearLayout.LayoutParams.WRAP_CONTENT
            }

            else -> return 0
        }
    }

    fun getLayoutParamHeight(height:Int): Int {
        when (height) {
            0 -> return LinearLayout.LayoutParams.WRAP_CONTENT

            else -> return ViewUlti.dpToPx(context, height)
        }
    }

}