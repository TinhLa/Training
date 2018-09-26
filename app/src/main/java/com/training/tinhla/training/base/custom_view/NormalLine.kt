package com.training.tinhla.training.base.custom_view

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.training.tinhla.training.base.ViewUlti
import com.training.tinhla.training.base.model.constant.CONTENT
import com.training.tinhla.training.base.model.json.ColumnModel
import com.training.tinhla.training.base.model.json.TemplateLineModel
import com.training.tinhla.training.splashscreen.helper.CreateViewHelper

/**
 * An NormalLine can contain either 1 column or 2 columns
 * It create ImageView or TextView depend on JSON data passed
 */
class NormalLine : LinearLayout {

    // JSONObject data determine UI
    lateinit var line: TemplateLineModel

    // width of ViewGroup parent contain NormalLine
    var parentWidth: Int = 10

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs : AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    constructor(context: Context, line: TemplateLineModel, parentWidth:Int) : super(context, null){
        this.line = line
        this.parentWidth = parentWidth

        orientation = LinearLayout.HORIZONTAL

        val columns = line.columns
        if (columns != null) {
            for (i in 0..(columns.size-1)){
                val column = columns.get(i)

                _addView(i, column)
            }

            // if there is only one column, add more 1 view width 10% width of parent
            if (columns.size == 1) {
                val view = View(context)
                val lp = LinearLayout.LayoutParams((parentWidth*.1f).toInt(), 1)
                view.layoutParams = lp
                addView(view)
            }
        }
    }

    private fun _addView(position: Int, column: ColumnModel){
        val contentType = column.contentType

        when (contentType) {
            CONTENT.TEXT.value -> {
                addTextView(column)
            }

            CONTENT.TITLE_NORMAL.value -> {
                addTitle(column)
            }

            else -> {
                addImageView(column)
            }
        }

        // set left margin if new child view is not the first
        if (position > 0) {
            val distance = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8f, resources.displayMetrics).toInt()
            val newView = getChildAt(position)
            newView.setPadding(distance,0,0,0)
        }
    }

    // Add a title view
    private fun addTitle(column: ColumnModel) {
        val titleView = TitleView(context, column, CreateViewHelper.panelWidth)

        val width = ViewUlti.getLayoutParamWidth(column.percentWidth)
        val lp = LinearLayout.LayoutParams(width, LinearLayout.LayoutParams.WRAP_CONTENT)
        if (width == 0) {
            lp.width = CreateViewHelper.panelWidth * column.percentWidth
        }

        titleView.maxWidth = (CreateViewHelper.panelWidth * 0.9f).toInt()

        titleView.layoutParams = lp

        addView(titleView)
    }

    // Add an image view
    private fun addImageView(column: ColumnModel){
        val imgv = NormalImageView(context, column, parentWidth)

        val lp = initLayoutParams(column)
        lp.gravity = Gravity.CENTER_VERTICAL
        imgv.layoutParams = lp

        addView(imgv)
    }

    // Add a text view
    private fun addTextView(column: ColumnModel){
        val tv = NormalTextView(context, column, parentWidth)

        val lp = initLayoutParams(column)
        lp.gravity = Gravity.CENTER_VERTICAL
        tv.layoutParams = lp
        tv.maxWidth = (parentWidth * 0.9f).toInt()
        tv.minWidth = (parentWidth * 0.1f).toInt()

        addView(tv)
    }

    private fun initLayoutParams(column:ColumnModel): LayoutParams {
        val width = ViewUlti.getLayoutParamWidth(column.percentWidth)
        val lp = LinearLayout.LayoutParams(width, ViewUlti.getLayoutParamHeight(context, column.height))

        if (width == 0) {
            var percent = column.percentWidth * 1f

            if (percent > 90f) {
                percent = 90f
            }
            else if (percent < 10f) {
                percent = 10f
            }
            lp.weight = percent/100f
        }
        return lp
    }
}