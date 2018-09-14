package com.training.tinhla.training.nkhoi_srcollview

import android.util.Log
import java.util.*
import kotlin.collections.ArrayList

class PresenterSliding :PresenterSlidingIpm {

    private var dbListText: DBListText?=null
    private var viewSliding : InterfaceSlidingActivity?=null
    constructor(interfaceSlidingActivity: InterfaceSlidingActivity){
        viewSliding = interfaceSlidingActivity
        dbListText = DBListText()
    }

    override fun getDBlistText() {
        var arrayList =  dbListText?.CreatListText()
        LoadTextOnView(arrayList!!)
    }

    override fun LoadTextOnView(arraylist : ArrayList<String>) {
        if(arraylist==null)
            viewSliding?.LoadTextFail("Thất Bại")
        else{
            viewSliding?.LoadTextSuccess(RanDomText(arraylist))
        }
    }
    override fun RanDomText(arraylist : ArrayList<String>) : String {
       var textAfterRanDom : String=""
       while(arraylist.size>0) {
           var i: Int =   Random().nextInt(arraylist.size)
           textAfterRanDom = textAfterRanDom + arraylist.get(i)+"\n"
           arraylist.removeAt(i)
       }
        return textAfterRanDom!!
    }
}