package com.training.tinhla.training.base.custom_view

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.training.tinhla.training.R
import com.training.tinhla.training.base.Ulti
import com.training.tinhla.training.base.ViewIdGenerator
import com.training.tinhla.training.base.model.json.ColumnJSON

class TitleView : ConstraintLayout {
    lateinit var data: ColumnJSON

    lateinit var iconView:ImageView
    lateinit var titleView: TextView
    lateinit var timeStampView:TextView

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs : AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    constructor(context: Context, data: ColumnJSON) : super(context, null){
        this.data = data

        if(data.parameter?.backgroundColor != null){
            setBackgroundColor(Color.parseColor(data.parameter?.backgroundColor))
        }

        if (data.parameter != null) {

            var parameter = data.parameter

            var matchConstrain = ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
            var wrapContent = ConstraintLayout.LayoutParams.WRAP_CONTENT

            iconView = ImageView(context)
            iconView.id = ViewIdGenerator.generate()

            iconView.layoutParams =
                    ConstraintLayout.LayoutParams(wrapContent, wrapContent)

            addView(iconView)
            Picasso.get().load(parameter?.icon).into(iconView)

            if (parameter?.title != null) {

                titleView = TextView(context)
                titleView.id = ViewIdGenerator.generate()

                titleView.layoutParams =
                        ConstraintLayout.LayoutParams(matchConstrain, wrapContent)

                titleView.setText(parameter!!.title)
                titleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
                titleView.setTextColor(Color.parseColor(parameter?.titleFontColor))

                // TODO : set font (missing)

                addView(titleView)

                if (parameter?.timeStamp != null) {

                    timeStampView = TextView(context)
                    timeStampView.id = ViewIdGenerator.generate()

                    timeStampView.layoutParams =
                            ConstraintLayout.LayoutParams(matchConstrain, wrapContent)

                    var time = Ulti.timeStampToDate(parameter!!.timeStamp!!.toLong())
                    timeStampView.setText(time.toString())
                    timeStampView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
                    timeStampView.setTextColor(Color.parseColor(parameter?.timeStampFontColor))

                    // TODO : set font (missing)

                    addView(timeStampView)
                }
            }

            var set = ConstraintSet()
            set.clone(this)

            var parentID = ConstraintSet.PARENT_ID
            var marginStart = Ulti.dpToPx(context, 8)

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