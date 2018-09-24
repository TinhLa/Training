package com.training.tinhla.training.base

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.widget.LinearLayout
import java.text.ParseException
import java.text.SimpleDateFormat

class ViewUlti {
    companion object {
        var density : Float = 0f

        fun getDensity(context: Context): Float {
            if (density.equals(0f)) {
                density = context.resources.displayMetrics.density
            }
            return density
        }

        fun dpToPx(context: Context, dp: Int): Int {
            getDensity(context)
            return Math.round(density * dp)
        }

        fun getLayoutParamWidth(percentWidth:Int): Int {
            when (percentWidth) {
                0 -> {
                    return LinearLayout.LayoutParams.WRAP_CONTENT
                }

                else -> return 0
            }
        }

        fun getLayoutParamHeight(context: Context, height:Int): Int {
            when (height) {
                0 -> return LinearLayout.LayoutParams.WRAP_CONTENT
                else -> return ViewUlti.dpToPx(context, height)
            }
        }

    }
}