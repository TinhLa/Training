package com.training.tinhla.training.base.custom_view

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import com.training.tinhla.training.base.model.constant.CONTENT
import com.training.tinhla.training.base.model.json.ColumnModel
import com.training.tinhla.training.base.model.json.TemplateLineModel

class TwoColumnsTextView : LinearLayout {
    lateinit var tv1 : TextView
    lateinit var tv2 : TextView

    lateinit var line: TemplateLineModel
    var parentWidth : Int = 0

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs : AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)
    constructor(context: Context, line: TemplateLineModel, parentWidth:Int) : super(context, null){
        this.line = line

        orientation = LinearLayout.HORIZONTAL

        var columns = line.columns
        if (columns != null) {
            for (i in 0..(columns.size-1)){
                var column = columns.get(i)
                var contentType = column.contentType
                when (contentType) {
                    CONTENT.TEXT.value -> {
                        addTextView(i, column)
                    }

                    CONTENT.IMAGE.value -> {
                        addImageView(column)
                    }
                }
            }
        }
    }

    private fun addImageView(column: ColumnModel) {
        var imgv = NormalImageView(context, column, width)
        var lp = imgv.layoutParams as LinearLayout.LayoutParams
        lp.gravity = Gravity.CENTER_VERTICAL

        addView(imgv)
    }

    private fun addTextView(position: Int, column: ColumnModel) {
        var tv = NormalTextView(context, column, width)

        var lp = tv.layoutParams as LinearLayout.LayoutParams
        lp.gravity = Gravity.CENTER_VERTICAL

        if (position > 0) {
            var distance = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4f, resources.displayMetrics).toInt()
            tv.setPadding(distance,0,0,0)
        }

        addView(tv)
    }
}