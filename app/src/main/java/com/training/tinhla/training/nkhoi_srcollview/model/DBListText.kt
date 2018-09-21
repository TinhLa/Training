package com.training.tinhla.training.nkhoi_srcollview.model

import com.training.tinhla.training.R

class DBListText  {
    fun creatListText():ArrayList<String>{
        val listText: ArrayList<String> = ArrayList<String>()
        for(i in 0 until 30 step 1){
            listText.add("Phần Tử thứ : " +i)
        }
        return listText
    }
}