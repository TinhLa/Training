package com.training.tinhla.training.nkhoi_srcollview

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.ViewGroup
import android.widget.LinearLayout
import com.training.tinhla.training.nkhoi_srcollview.model.Data
import com.training.tinhla.training.nkhoi_srcollview.model.ReadJson

class JsonPresenterTemplateLinesImpl(var context: Context) : JsonInterfaceTemplateLines.JsonPresenterTemplateLines {



    private val dta : Data
    private val readJson : ReadJson
    private val  textjson : String
    private val paint : Paint

    init {
        readJson = ReadJson(context)
        textjson = readJson.readFileJsonFromAsset(context)
        dta = readJson.parseJsonInData()
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
    }
    override fun getPropertyForColumn(view: ViewGroup) {
       for(i in 0..(dta.templateBody?.templateLines?.size!! -1) step  1){
           val lineType: String? = dta.templateBody?.templateLines?.get(i)?.lineType
         when(lineType){
             "normal"-> setTypeLine(view, i)
             "emptyLine"->setTypeLineEmptyLine(view)
             "drawLine"->setTypeLineDrawLine(view)
         }
       }
    }

    override fun setTypeLine(view: ViewGroup , position: Int) {
        val numberColumn: Int? = dta.templateBody?.templateLines?.get(position)?.columns?.size
        if(numberColumn==2) setTypeLineTwoColum(view,position) else setTypeLineOneColumn(view,position)
    }

    override fun setTypeLineOneColumn(view: ViewGroup, position: Int) {
        val height: Int? = dta.templateBody?.templateLines?.get(position)?.columns?.get(0)?.height
        val alignment: String? = dta.templateBody?.templateLines?.get(position)?.columns?.get(0)?.alignment
        val verticalAlignment: String? = dta.templateBody?.templateLines?.get(position)?.columns?.get(0)?.verticalAlignment
        val contentType: String? = dta.templateBody?.templateLines?.get(position)?.columns?.get(0)?.contentType
    }

    override fun setTypeLineTwoColum(view: ViewGroup, position: Int) {

    }
    override fun setTypeLineDrawLine(view: ViewGroup) {
        val linearLayout: LinearLayout = LinearLayout(context)
        val param = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,10)
        linearLayout.layoutParams = param
        linearLayout.setBackgroundColor(Color.parseColor("#b6b6b6")) // gray color
        view.addView(linearLayout)
    }

    override fun setTypeLineEmptyLine(view: ViewGroup) {
        val linearLayout: LinearLayout = LinearLayout(context)
        val param = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,50)
        linearLayout.layoutParams = param
        linearLayout.setBackgroundColor(Color.parseColor("#ffffff"))
        view.addView(linearLayout)
    }


    override fun getParameterIcon(viewGroup: ViewGroup, position: Int, percentWidth: Int?, height: Int?, alignment: String?, verticalAlignment: String?) {

    }

    override fun getParameterText(viewGroup: ViewGroup, position: Int, percentWidth: Int?, height: Int?, alignment: String?, verticalAlignment: String?) {

    }
}