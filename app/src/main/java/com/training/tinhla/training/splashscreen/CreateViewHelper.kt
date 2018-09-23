package com.training.tinhla.training.splashscreen

import android.graphics.Color
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.training.tinhla.training.base.custom_view.*
import com.training.tinhla.training.base.model.json.ButtonModel
import com.training.tinhla.training.base.model.json.ColumnModel
import com.training.tinhla.training.base.model.json.TemplateLineModel

class CreateViewHelper {
    companion object {
        fun addImageViewToHeader(parent: ViewGroup, data: ColumnModel) {
            var imageView = NormalImageView(parent.context, data, parent.width)

            var lp = imageView.layoutParams as LinearLayout.LayoutParams
            lp.topMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8f, parent.resources.displayMetrics).toInt()
            imageView.layoutParams = lp

            parent.addView(imageView)
        }

        fun addTextViewToHeader(parent: ViewGroup, data: ColumnModel) {
            var textView = NormalTextView(parent.context, data, parent.width)

            var margin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8f, parent.resources.displayMetrics).toInt()
            var lp = textView.layoutParams as LinearLayout.LayoutParams
            lp.topMargin = margin

            parent.addView(textView)
        }

        fun addTitleViewToBody(parent: LinearLayout?, columnModel: ColumnModel) {
            var titleView = TitleView(parent!!.context, columnModel)

            var lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            var margin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4f, parent.resources.displayMetrics).toInt()

            lp.topMargin = margin
            lp.bottomMargin = margin
            titleView.layoutParams = lp

            parent.addView(titleView)
        }

        fun addTwoColumnsInBodyLine(parent: LinearLayout, lineData: TemplateLineModel) {
            var view = TwoColumnsTextView(parent.context, lineData, parent.width)

            var margin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4f, parent.resources.displayMetrics).toInt()
            var lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            lp.setMargins(0, margin, 0, margin)
            view.layoutParams = lp

            parent.addView(view)
        }

        fun addDrawLine(parent: LinearLayout) {
            var context = parent.context

            var lineView = View(context)
            lineView.setBackgroundColor(Color.GRAY)
            var height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, parent.resources.displayMetrics).toInt()
            var margin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4f, parent.resources.displayMetrics).toInt()
            var lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height)
            lp.setMargins(0, margin, 0, margin)

            lineView.layoutParams = lp

            parent.addView(lineView)
        }

        fun addEmptyLine(parent: LinearLayout) {
            var context = parent.context
            var emptyLine = View(context)

            var height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, parent.resources.displayMetrics).toInt()
            var margin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6f, parent.resources.displayMetrics).toInt()
            var lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height)
            lp.setMargins(0, margin, 0, margin)

            emptyLine.layoutParams = lp

            parent.addView(emptyLine)
        }

        fun createNewButtons(parent: RelativeLayout, buttons: ArrayList<ButtonModel>) {
            var count = buttons.size

            when (count) {
                1 -> {
                    createOnTemplateButton(parent, buttons.get(0))
                }

                2 -> {
                    createTwoTemplateButtons(parent, buttons)
                }
            }
        }

        private fun createOnTemplateButton(parent: RelativeLayout, data: ButtonModel) {
            var button = TemplateButton(parent.context, data)

            var lp = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
            lp.addRule(RelativeLayout.CENTER_IN_PARENT)

            button.layoutParams = lp

            parent.addView(button)
        }

        private fun createTwoTemplateButtons(parent: RelativeLayout, buttons: ArrayList<ButtonModel>) {
            for (i in 0..1) {
                var data = buttons.get(i)
                var button = TemplateButton(parent.context, data)
                var lp = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)

                when (i) {
                    0 -> {
                        lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT)
                    }

                    1 -> {
                        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
                    }
                }

                button.layoutParams = lp

                parent.addView(button)
            }
        }

    }
}