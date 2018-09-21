package com.training.tinhla.training.nkhoi_srcollview

import android.content.Context
import android.graphics.Color
import android.text.Html
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.training.tinhla.training.nkhoi_srcollview.model.DBListText
import com.training.tinhla.training.nkhoi_srcollview.model.Data
import com.training.tinhla.training.nkhoi_srcollview.model.ReadJson
import java.text.FieldPosition
import java.util.*

class JsonPresenterImpl(var context: Context,var view :SlidingInterface.viewSliding) : JsonInterface.presenterJson {

    private val dta : Data
    private val readJson : ReadJson
    private val  textjson : String
    private var dbListText : DBListText
    init {
        readJson = ReadJson(context)
        textjson = readJson.readFileJsonFromAsset(context)
        dbListText = DBListText()
        dta = readJson.parseJsonInData()
    }

    override fun getDBlistText() {
        loadTextOnView(dbListText.creatListText())
    }

    override fun getListImageViewPager() : List<String> {
       // return  dta.getTemplateBody().getIframeProperty().getListImage()
        val list: List<String> = listOf("a")
        return list
    }
    override fun loadTextOnView(arrayListString: ArrayList<String>) {
        view.loadTextSuccess(ranDomText(arrayListString))
    }

    override fun getPropertyForColumnIframe(layoutParams : ViewGroup.LayoutParams, position: Int): ViewGroup.LayoutParams {
//        val lineType :String = dta.getTemplateBody().getIframeProperty().getTemplateLines().get(position).getLineType()
//        val percentWidth : Int = dta.getTemplateBody().getIframeProperty().getTemplateLines().get(position).getColumns().get(0).getColumnPercentWidth()
//        val height : Int = dta.getTemplateBody().getIframeProperty().getTemplateLines().get(position).getColumns().get(0).getColumnHeight()
//        val alignment : String = dta.getTemplateBody().getIframeProperty().getTemplateLines().get(position).getColumns().get(0).getColumnAlignment()
//        val verticalAlignment : String = dta.getTemplateBody().getIframeProperty().getTemplateLines().get(position).getColumns().get(0).getColumnVerticalAlignment()
//        val contentType : String  = dta.getTemplateBody().getIframeProperty().getTemplateLines().get(position).getColumns().get(0).getColumnContentType()
////        when(contentType){
////            "image"->
////
////        }
        return layoutParams
    }

    override fun getParametterImageIframe(layoutParams : ViewGroup.LayoutParams,lineType: String, percentWidth: Int, height: Int, alignment: String, verticalAlignment: String): ViewGroup.LayoutParams {
      //   val url : String  =  dta.getTemplateBody().getIframeProperty().getTemplateLines().get(0).getColumns().get(0).getColumnParameter().getParameterUrl()

        return layoutParams
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
}