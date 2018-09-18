package com.training.tinhla.training.nkhoi_srcollview

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import android.widget.TextView

open class SlidingInterface {

    interface presenterSliding {
        fun getDBlistText()
        fun LoadTextOnView(arrayListString : ArrayList<String>)
        fun RanDomText(arraylist : ArrayList<String>): String
        fun loadRecycler(recycler : RecyclerView , context : Context)
        fun setImageIfarmeProperty (img : ImageView, url : String)
        fun setTextIfarmeProperty (textView : TextView ,text : String, color : Int , style : Int)
    }

    interface viewSliding {
        fun LoadTextSuccess(text :String)
        fun LoadTextFail(text :String)
    }
}