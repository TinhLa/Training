package com.training.tinhla.training.nkhoi_srcollview.presenter

import android.content.Context
import android.support.v4.view.ViewPager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

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