package com.training.tinhla.training.base.custom_view

import android.content.Context
import android.graphics.Color
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.training.tinhla.training.R
import com.training.tinhla.training.base.Ulti
import com.training.tinhla.training.base.ViewUlti
import com.training.tinhla.training.base.ViewIdGenerator
import com.training.tinhla.training.base.model.json.ColumnModel

class TitleView : ConstraintLayout {
    lateinit var data: ColumnModel
    var parentWidth = 0

    lateinit var iconView:ImageView
    lateinit var titleView: TextView
    lateinit var timeStampView:TextView

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs : AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    constructor(context: Context, data: ColumnModel, parentWidth:Int) : super(context, null){
        this.data = data
        this.parentWidth = parentWidth

        maxWidth = (parentWidth * 0.9f).toInt()

        if(data.parameter?.backgroundColor != null){
            setBackgroundColor(Color.parseColor(data.parameter?.backgroundColor))
        }

        if (data.parameter != null) {

            val parameter = data.parameter

            val matchConstrain = ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
            val wrapContent = ConstraintLayout.LayoutParams.WRAP_CONTENT

            iconView = ImageView(context)
            iconView.id = ViewIdGenerator.generate()

            val iconSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24f, resources.displayMetrics).toInt()

            iconView.layoutParams =
                    ConstraintLayout.LayoutParams(iconSize, iconSize)

            addView(iconView)

            if (parameter?.icon == null || parameter.icon.equals("")) {
                Glide.with(this).load(R.drawable.ic_title_default).into(iconView)
            }else{
                Glide.with(this).load(parameter.icon).into(iconView)
            }

            if (parameter?.title != null) {

                titleView = TextView(context)
                titleView.id = ViewIdGenerator.generate()

                titleView.layoutParams =
                        ConstraintLayout.LayoutParams(matchConstrain, wrapContent)

                titleView.setText(parameter.title)
                titleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)

                if (parameter.titleFontColor != null) {
                    titleView.setTextColor(Color.parseColor(parameter.titleFontColor))
                }

                // TODO : set font (missing)

                addView(titleView)

                if (parameter.timeStamp != null) {

                    timeStampView = TextView(context)
                    timeStampView.id = ViewIdGenerator.generate()

                    timeStampView.layoutParams =
                            ConstraintLayout.LayoutParams(matchConstrain, wrapContent)

                    val time = Ulti.timeStampToDate((parameter.timeStamp?:"0").toLong())
                    timeStampView.setText(time)
                    timeStampView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)

                    if (!Ulti.isEmptyStr(parameter.timeStampFontColor)) {
                        timeStampView.setTextColor(Color.parseColor(parameter.timeStampFontColor))
                    }

                    // TODO : set font (missing)

                    addView(timeStampView)
                }
            }

            val set = ConstraintSet()
            set.clone(this)

            val parentID = ConstraintSet.PARENT_ID
            val marginStart = ViewUlti.dpToPx(context, 8)

            set.connect(iconView.id, ConstraintSet.START, parentID, ConstraintSet.START)
            set.connect(iconView.id, ConstraintSet.TOP, parentID, ConstraintSet.TOP)
            set.connect(iconView.id, ConstraintSet.BOTTOM, parentID, ConstraintSet.BOTTOM)

            set.connect(titleView.id, ConstraintSet.TOP, parentID, ConstraintSet.TOP)
            set.connect(titleView.id, ConstraintSet.START, iconView.id, ConstraintSet.END, marginStart)
            set.connect(titleView.id, ConstraintSet.END, parentID, ConstraintSet.END)

            set.connect(timeStampView.id, ConstraintSet.TOP, titleView.id, ConstraintSet.BOTTOM)
            set.connect(timeStampView.id, ConstraintSet.START, titleView.id, ConstraintSet.START)
            set.connect(timeStampView.id, ConstraintSet.END, titleView.id, ConstraintSet.END)

            set.applyTo(this)
        }

    }

}