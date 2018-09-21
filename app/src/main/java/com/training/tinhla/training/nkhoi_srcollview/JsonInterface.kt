package com.training.tinhla.training.nkhoi_srcollview

import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

open  class JsonInterface {
    interface presenterJson{
        fun getDBlistText()
        fun getListImageViewPager() : List<String>
        fun getPropertyForColumnIframe(layoutParams : ViewGroup.LayoutParams, position :Int) : ViewGroup.LayoutParams
        fun getParametterImageIframe(layoutParams : ViewGroup.LayoutParams,lineType :String ,percentWidth : Int,height : Int,
                                     alignment : String,verticalAlignment : String): ViewGroup.LayoutParams

        //old
        fun loadTextOnView(arrayListString : ArrayList<String>)
        fun ranDomText(arraylist : ArrayList<String>): String
    }
}