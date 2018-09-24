package com.training.tinhla.training.nkhoi_srcollview

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

open  class JsonInterfaceIframe {
    interface presenterJson{
        fun getListImageViewPager() : List<String>?
        fun getPropertyForColumnIframe(view : ViewGroup,position :Int)
        fun getParameterImageIframe(viewGroup: ViewGroup,position: Int,percentWidth : Int?,height : Int?,
                                     alignment : String?,verticalAlignment : String?)
        fun getParameterTextIframe(viewGroup: ViewGroup,position: Int,percentWidth : Int?,height : Int?,
                                   alignment : String?,verticalAlignment : String?)
    }
}