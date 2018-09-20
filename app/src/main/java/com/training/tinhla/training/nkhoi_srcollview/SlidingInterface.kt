package com.training.tinhla.training.nkhoi_srcollview

import android.content.Context
import android.support.v4.view.ViewPager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

open class SlidingInterface {

    interface presenterSliding {
        fun getDBlistText()
        fun getListImageViewPager() : ArrayList<Int>
        fun creatDotsOfViewPager(context: Context, view: LinearLayout , number : Int)
        fun onPageChangeViewPager(context: Context, numberDots: Int, position: Int, view: LinearLayout)
        fun loadTextOnView(arrayListString : ArrayList<String>)
        fun ranDomText(arraylist : ArrayList<String>): String
        fun setImageIfarmeProperty (img : ImageView, url : String)
        fun setTextIfarmeProperty (textView : TextView ,text : String, color : Int , style : Int)
    }

    interface viewSliding {
        fun loadTextSuccess(text :String)
        fun loadTextFail(text :String)
    }
}