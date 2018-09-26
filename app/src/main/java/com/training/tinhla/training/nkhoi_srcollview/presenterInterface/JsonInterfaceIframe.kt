package com.training.tinhla.training.nkhoi_srcollview.presenterInterface

import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

open  class JsonInterfaceIframe {
    interface presenterJson{
        // Iframme
        fun getListImageViewPager() : List<String>?
        fun getPropertyForColumnIframe(view : ViewGroup)

        // templateLine Body
        fun getPropertyForColumnTemplateLines(viewGroup: ViewGroup){}
        fun setTypeLine(view: ViewGroup,position: Int){}
        fun setTypeLineOneColumn(view: ViewGroup,position: Int){}
        fun setTypeLineTwoColum(view: ViewGroup,position: Int){}
        fun setTypeLineDrawLine(view: ViewGroup){}
        fun setTypeLineEmptyLine(view: ViewGroup){}
        fun getParameterImageOneColumn(viewGroup: ViewGroup,positionTemplateLine: Int,positionColumn: Int,percentWidth : Int?,height : Int?,
                              alignment : String?,verticalAlignment : String?){}

        // public
        fun getParameterIcon(viewGroup: ViewGroup,positionTemplateLine: Int,percentWidth : Int?,height : Int?,
                              alignment : String?,verticalAlignment : String?){}
        fun getParameterImage(viewGroup: ViewGroup,positionTemplateLine: Int,positionColumn: Int,percentWidth : Int?,height : Int?,
                              alignment : String?,verticalAlignment : String?){}
        fun getParameterListImage(viewGroup: ViewGroup,positionTemplateLine: Int,positionColumn: Int,percentWidth : Int?,height : Int?,
                              alignment : String?,verticalAlignment : String?){}
        fun getParameterText(viewGroup: ViewGroup,positionTemplateLine: Int,positionColumn: Int,percentWidth : Int?,height : Int?,
                             alignment : String?,verticalAlignment : String?){}
        fun getParameterTitle(viewGroup: ViewGroup,positionTemplateLine: Int,percentWidth : Int?,height : Int?,
                             alignment : String?,verticalAlignment : String?){}
        fun setPercenwidth(percen: Int?) :Int
        fun setHeight(height: Int?) :Int
        fun setAlignment(linearLayout: LinearLayout,alignment: String?)
        fun setFontStyle(textView: TextView, fontStyle: String?)
        fun setFontSize(textView: TextView,fontSize :String?)
    }
}