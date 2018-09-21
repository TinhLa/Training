package com.training.tinhla.training.base

import android.app.Activity
import android.app.Application
import android.content.Context
import android.graphics.Point
import java.math.BigDecimal
import java.math.BigInteger
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Ulti {
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

        fun getScreenWidth(activity: Activity) {
            var display = activity.windowManager.defaultDisplay
            var point = Point()
            display.getSize(point)

        }

        fun timeStampToDate(timeStamp: Long) : String {
            var sdf = SimpleDateFormat("dd/MM/yyyy hh:mm a")

            var date:String=""
            try {
                date = sdf.format(timeStamp)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return date
        }
    }
}