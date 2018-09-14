package com.training.tinhla.training.nkhoi_srcollview

import java.util.*
import kotlin.collections.ArrayList

class SlidingPresenterImpl(var view :SlidingInterface.viewSliding): SlidingInterface.presenterSliding {
     private lateinit var dbListText : DBListText

    init {
          dbListText = DBListText()
    }
    override fun getDBlistText() {
       LoadTextOnView(dbListText.CreatListText())
    }

    override fun LoadTextOnView(arrayListString: ArrayList<String>) {
       view.LoadTextSuccess(RanDomText(arrayListString))
    }

    override fun RanDomText(arraylist: ArrayList<String>): String {
       var text : String  =""
        while (arraylist.size>0){
            val i:Int = Random().nextInt(arraylist.size)
            text = text + arraylist.get(i) +"\n"
            arraylist.removeAt(i)
        }
        return text
    }


}