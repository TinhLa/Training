package com.training.tinhla.training.nkhoi_srcollview.presenterInterface

import android.content.Context
import android.widget.LinearLayout

open class SlidingInterface {

    interface presenterSliding {
        fun creatDotsOfViewPager(context: Context, view: LinearLayout, number : Int)
        fun onPageChangeViewPager(context: Context, numberDots: Int, position: Int, view: LinearLayout)
    }

    interface viewSliding {
        fun loadTextSuccess(text :String)
        fun loadTextFail(text :String)
    }
}