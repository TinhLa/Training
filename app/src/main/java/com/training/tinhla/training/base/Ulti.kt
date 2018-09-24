package com.training.tinhla.training.base

import java.text.ParseException
import java.text.SimpleDateFormat

class Ulti {
    companion object {

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