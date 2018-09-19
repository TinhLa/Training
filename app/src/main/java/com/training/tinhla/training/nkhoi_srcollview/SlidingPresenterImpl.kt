package com.training.tinhla.training.nkhoi_srcollview


import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.training.tinhla.training.R
import com.training.tinhla.training.nkhoi_srcollview.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.include_viewpager.*
import java.util.*
import kotlin.collections.ArrayList

class SlidingPresenterImpl(var view :SlidingInterface.viewSliding): SlidingInterface.presenterSliding {
    override fun creatdotsOfViewpager(context : Context ,view: LinearLayout , number :Int) {
            for(i in 1..number step 1) {
                var txt: TextView = TextView(context)
                txt.text = Html.fromHtml("&#8226;")
                txt.setTextSize(30F)
                txt.setTextColor(Color.rgb(180, 180, 180)) // gray color
                view.addView(txt)
            }
    }

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