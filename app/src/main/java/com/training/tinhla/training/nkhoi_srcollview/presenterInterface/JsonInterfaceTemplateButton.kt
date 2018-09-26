package com.training.tinhla.training.nkhoi_srcollview.presenterInterface

import android.view.ViewGroup

open class JsonInterfaceTemplateButton {
    interface presenterTemplateBnt{
        fun getListButton(viewGroup: ViewGroup)
        fun setOneButton(viewGroup: ViewGroup)
        fun setTwoButton(viewGroup: ViewGroup)
        fun checkEnableOneButton(viewGroup: ViewGroup,state :String)
        fun checkEnableTwoButton(viewGroup: ViewGroup,state1 :String,state2:String)
    }
}