package com.training.tinhla.training.nkhoi_srcollview


import android.content.Context
import android.graphics.Color
import android.text.Html
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.training.tinhla.training.nkhoi_srcollview.model.DBListText
import java.util.*
import kotlin.collections.ArrayList

class SlidingPresenterImpl(var view :SlidingInterface.viewSliding): SlidingInterface.presenterSliding {

    private var dbListText : DBListText

    init {
          dbListText = DBListText()
    }
    override fun getDBlistText() {
       loadTextOnView(dbListText.creatListText())
    }

    override fun getListImageViewPager() : ArrayList<Int> {
       return dbListText.creatLístImageViewPager()
    }

    override fun loadTextOnView(arrayListString: ArrayList<String>) {
       view.loadTextSuccess(ranDomText(arrayListString))
    }

    override fun creatDotsOfViewPager(context : Context ,view: LinearLayout , number :Int) {
        for(i in 0..(number-1) step 1) {
            var txt: TextView = TextView(context)
            txt.text = Html.fromHtml("&#8226;")
            txt.setTextSize(30F)
            txt.setTextColor(Color.rgb(180, 180, 180)) // gray color
            if(i==0) txt.setTextColor(Color.rgb(255, 255, 255)) // white color
            view.addView(txt)
        }
    }

    override fun onPageChangeViewPager(context: Context, numberDots: Int, position: Int, view: LinearLayout) {
        view.removeAllViews()
        for(i in 0..(numberDots-1) step 1) {
            var txt: TextView = TextView(context)
            txt.text = Html.fromHtml("&#8226;")
            txt.setTextSize(30F)
            if(i==position) txt.setTextColor(Color.rgb(255, 255, 255)) // white color
            else txt.setTextColor(Color.rgb(180, 180, 180)) // gray color
            view.addView(txt)
        }
    }


    override fun ranDomText(arraylist: ArrayList<String>): String {
       var text : String  =""
        while (arraylist.size>0){
            val i:Int = Random().nextInt(arraylist.size)
            text = text + arraylist.get(i) +"\n"
            arraylist.removeAt(i)
        }
        return text
    }
    override fun setImageIfarmeProperty(img: ImageView, url: String) {

    }

    override fun setTextIfarmeProperty(textView: TextView, text: String, color: Int, style: Int) {

    }

}