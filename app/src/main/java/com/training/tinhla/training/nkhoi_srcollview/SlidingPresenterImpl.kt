package com.training.tinhla.training.nkhoi_srcollview


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.training.tinhla.training.R
import com.training.tinhla.training.nkhoi_srcollview.adapter.recyclerAdapter
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
    override fun loadRecycler(recycler : RecyclerView , context : Context) {
        var array : ArrayList<Int> = ArrayList()
        array.add(R.drawable.ntkh_image7)
        array.add(R.drawable.ntkn_image4)
        array.add(R.drawable.ntkn_image5)
        array.add(R.drawable.ntkn_image6)
        val adpter : recyclerAdapter = recyclerAdapter(context , array)
        recycler.adapter = adpter
    }
    override fun setImageIfarmeProperty(img: ImageView, url: String) {

    }

    override fun setTextIfarmeProperty(textView: TextView, text: String, color: Int, style: Int) {

    }

}