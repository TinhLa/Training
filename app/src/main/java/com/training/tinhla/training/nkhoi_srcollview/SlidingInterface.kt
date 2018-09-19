package com.training.tinhla.training.nkhoi_srcollview

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

open class SlidingInterface {

    interface presenterSliding {
        fun getDBlistText()
        fun getListImageViewPager() : ArrayList<Int>
        fun creatdotsOfViewpager(context: Context, view: LinearLayout , number : Int)
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