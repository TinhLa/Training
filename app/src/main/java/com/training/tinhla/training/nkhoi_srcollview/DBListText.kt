package com.training.tinhla.training.nkhoi_srcollview

import android.util.Log

class DBListText  {
    fun CreatListText():ArrayList<String>{
        val listText: ArrayList<String> = ArrayList<String>()
        var i : Int = 0
        for(i in 0 until 30 step 1){
            listText.add("Phần Tử thứ : " +i)
        }
        return listText
    }
}