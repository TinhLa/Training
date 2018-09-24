package com.training.tinhla.training.nkhoi_srcollview

import android.view.ViewGroup

open class JsonInterfaceTemplateLines {
    interface JsonPresenterTemplateLines {
        fun getPropertyForColumn(view: ViewGroup)
        fun setTypeLine(view: ViewGroup,position: Int)
        fun setTypeLineOneColumn(view: ViewGroup,position: Int)
        fun setTypeLineTwoColum(view: ViewGroup,position: Int)
        fun setTypeLineDrawLine(view: ViewGroup)
        fun setTypeLineEmptyLine(view: ViewGroup)
        fun getParameterIcon(viewGroup: ViewGroup, position: Int, percentWidth: Int?, height: Int?,
                             alignment: String?, verticalAlignment: String?)

        fun getParameterText(viewGroup: ViewGroup, position: Int, percentWidth: Int?, height: Int?,
                             alignment: String?, verticalAlignment: String?)
    }
}