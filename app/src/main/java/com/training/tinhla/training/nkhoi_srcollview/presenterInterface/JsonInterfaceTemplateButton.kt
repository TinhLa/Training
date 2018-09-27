package com.training.tinhla.training.nkhoi_srcollview.presenterInterface

import android.view.ViewGroup

open class JsonInterfaceTemplateButton {
    interface presenterTemplateBnt{
        fun getListButton(viewGroup: ViewGroup)
        fun setOneButton(viewGroup: ViewGroup , icon : String , textKey :String)
        fun setTwoButton(viewGroup: ViewGroup, icon1 : String , textKey1 :String, icon2 : String , textKey2 :String)
        fun checkEnableOneButton(viewGroup: ViewGroup,state :String , icon : String , textKey :String)
        fun checkEnableTwoButton(viewGroup: ViewGroup,state1 :String,icon1 : String , textKey1 :String,state2:String, icon2 : String , textKey2 :String)
    }
}