package com.training.tinhla.training.nkhoi_srcollview

open class SlidingInterface {

    interface presenterSliding {
        fun getDBlistText()
        fun LoadTextOnView(arrayListString : ArrayList<String>)
        fun RanDomText(arraylist : ArrayList<String>): String
    }

    interface viewSliding {
        fun LoadTextSuccess(text :String)
        fun LoadTextFail(text :String)
    }
}