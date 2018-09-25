package com.training.tinhla.training.nkhoi_srcollview

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

open  class JsonInterfaceIframe {
    interface presenterJson{
        // Iframme
        fun getListImageViewPager() : List<String>?
        fun getPropertyForColumnIframe(view : ViewGroup)

        // templateLine Body
        fun getPropertyForColumnTemplateLines(viewGroup: ViewGroup)
        fun setTypeLine(view: ViewGroup,position: Int)
        fun setTypeLineOneColumn(view: ViewGroup,position: Int)
        fun setTypeLineTwoColum(view: ViewGroup,position: Int)
        fun setTypeLineDrawLine(view: ViewGroup)
        fun setTypeLineEmptyLine(view: ViewGroup)

        // public
        fun getParameterIcon(viewGroup: ViewGroup,positionTemplateLine: Int,percentWidth : Int?,height : Int?,
                              alignment : String?,verticalAlignment : String?)
        fun getParameterImage(viewGroup: ViewGroup,positionTeamplate: Int,positionColumn: Int,percentWidth : Int?,height : Int?,
                              alignment : String?,verticalAlignment : String?)
        fun getParameterListImage(viewGroup: ViewGroup,positionTeamplate: Int,positionColumn: Int,percentWidth : Int?,height : Int?,
                              alignment : String?,verticalAlignment : String?)
        fun getParameterText(viewGroup: ViewGroup,positionTemplate: Int,positionColumn: Int,percentWidth : Int?,height : Int?,
                             alignment : String?,verticalAlignment : String?)
        fun getParameterTitle(viewGroup: ViewGroup,position: Int,percentWidth : Int?,height : Int?,
                             alignment : String?,verticalAlignment : String?)
        fun setPercenwidth(percen: Int?) :Int
        fun setHeight(height: Int?) :Int
        fun setAlignment(imageView: ImageView,alignment: String?)
        fun setAlignment(textView: TextView, alignment: String?)
        fun setFontStyle(textView: TextView, fontStyle: String?)
    }
}