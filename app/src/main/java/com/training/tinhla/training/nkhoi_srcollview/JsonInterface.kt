package com.training.tinhla.training.nkhoi_srcollview

import com.training.tinhla.training.nkhoi_srcollview.model.ColumnIframeProperty
import com.training.tinhla.training.nkhoi_srcollview.model.TemplateButton

open  class JsonInterface {
    interface presenterJson{
        fun getTemplateID() : String
        fun getTemplateVersion() : String
        fun getTemplateButton():ArrayList<TemplateButton>
        fun getTemplateBodyTye():String
        fun getListImageIframeProperty():ArrayList<String>
      //  fun getColumnsTemplateLines():ArrayList<ColumnIframeProperty>
    }
}