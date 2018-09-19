package com.training.tinhla.training.nkhoi_srcollview

import android.util.Log
import com.training.tinhla.training.R

class DBListText  {
    fun creatListText():ArrayList<String>{
        val listText: ArrayList<String> = ArrayList<String>()
        for(i in 0 until 30 step 1){
            listText.add("Phần Tử thứ : " +i)
        }
        return listText
    }

    fun creatLístImageViewPager():ArrayList<Int>{
        val listImage : ArrayList<Int> = ArrayList<Int>()
        listImage.add(R.drawable.ntkn_image4)
        listImage.add(R.drawable.ntkn_image5)
        listImage.add(R.drawable.ntkn_image6)
        listImage.add(R.drawable.ntkh_image7)
        return  listImage
    }
}