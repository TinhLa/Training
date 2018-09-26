package com.training.tinhla.training.splashscreen

import android.app.Activity
import android.graphics.Color
import android.graphics.Point
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.training.tinhla.training.R
import com.training.tinhla.training.base.custom_view.*
import com.training.tinhla.training.base.model.json.ButtonModel
import com.training.tinhla.training.base.model.json.TemplateLineModel

class CreateViewHelper {
    companion object {
        var panelWidth:Int = 0

        private fun measurePanelWidth(activity: Activity, panel: LinearLayout): Int {
            val point = Point()
            val display = activity.windowManager.defaultDisplay
            display.getSize(point)
            val screenWidth = point.x
            var delta = 0

            var view = panel as View

            while (view.id != R.id.main_gv) {

                val padDelta = view.paddingLeft + view.paddingRight
                val lp = view.layoutParams as ViewGroup.MarginLayoutParams
                val marginDelta = lp.leftMargin + lp.rightMargin
                val _delta = padDelta + marginDelta

                delta += _delta

                if (view.parent == null) {
                    break
                }
                view = view.parent as View
            }

            return screenWidth - delta
        }

        fun addLine(activity: Activity, parent: LinearLayout, line: TemplateLineModel) {

            if (panelWidth == 0) {
                panelWidth = measurePanelWidth(activity, parent)
            }

            val view = NormalLine(parent.context, line, panelWidth)

            // add margin top and bottom for new NormalLine
            val margin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4f, parent.resources.displayMetrics).toInt()
            val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            lp.setMargins(0, margin, 0, margin)
            view.layoutParams = lp

            parent.addView(view)
        }

        fun addDrawLine(parent: LinearLayout) {
            val context = parent.context

            val lineView = View(context)
            lineView.setBackgroundColor(Color.GRAY)
            val height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, parent.resources.displayMetrics).toInt()
            val margin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4f, parent.resources.displayMetrics).toInt()
            val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height)
            lp.setMargins(0, margin, 0, margin)

            lineView.layoutParams = lp

            parent.addView(lineView)
        }

        fun addEmptyLine(parent: LinearLayout) {
            val context = parent.context
            val emptyLine = View(context)

            val height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, parent.resources.displayMetrics).toInt()
            val margin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6f, parent.resources.displayMetrics).toInt()
            val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height)
            lp.setMargins(0, margin, 0, margin)

            emptyLine.layoutParams = lp

            parent.addView(emptyLine)
        }

        private fun createOnTemplateButton(parent: RelativeLayout, data: ButtonModel) {
            val button = TemplateButton(parent.context, data)

            val lp = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
            lp.addRule(RelativeLayout.CENTER_IN_PARENT)

            button.layoutParams = lp

            parent.addView(button)
        }

        private fun createTwoTemplateButtons(parent: RelativeLayout, buttons: ArrayList<ButtonModel>) {
            for (i in 0..1) {
                val data = buttons.get(i)
                val button = TemplateButton(parent.context, data)
                val lp = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)

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

        fun addTemplateButtons(parent:RelativeLayout, buttons: ArrayList<ButtonModel>) {
            val count = buttons.size

            when (count) {
                1 -> {
                    createOnTemplateButton(parent, buttons.get(0))
                }

                2 -> {
                    createTwoTemplateButtons(parent, buttons)
                }
            }
        }
    }
}