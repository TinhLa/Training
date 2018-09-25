package com.training.tinhla.training.nkhoi_srcollview

import android.annotation.TargetApi
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.training.tinhla.training.R
import com.training.tinhla.training.nkhoi_srcollview.model.Data
import com.training.tinhla.training.nkhoi_srcollview.model.ReadJson
import java.util.*
import java.text.SimpleDateFormat


class JsonPresenterTemplateLinesImpl(context: Context) : JsonPresenterIframeImpl(context) {
    private val dta : Data
    private val readJson : ReadJson
    private val  textjson : String
    init {
        readJson = ReadJson(context)
        textjson = readJson.readFileJsonFromAsset(context)
        dta = readJson.parseJsonInData()
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun getPropertyForColumnTemplateLines(viewGroup: ViewGroup) {
        for(i in 0..(dta.templateBody?.templateLines?.size!! -1) step  1){
            val lineType: String? = dta.templateBody?.templateLines?.get(i)?.lineType
            when(lineType){
                "normal"-> setTypeLine(viewGroup, i)
                "emptyLine"->setTypeLineEmptyLine(viewGroup)
                "drawLine"->setTypeLineDrawLine(viewGroup)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun setTypeLine(view: ViewGroup , position: Int) {
        val numberColumn: Int? = dta.templateBody?.templateLines?.get(position)?.columns?.size
        if(numberColumn==2) setTypeLineTwoColum(view,position) else setTypeLineOneColumn(view,position)
    }

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun setTypeLineOneColumn(view: ViewGroup, position: Int) {
        val percentWidth: Int? = dta.templateBody?.templateLines?.get(position)?.columns?.get(0)?.percentWidth
        val height: Int? = dta.templateBody?.templateLines?.get(position)?.columns?.get(0)?.height
        val alignment: String? = dta.templateBody?.templateLines?.get(position)?.columns?.get(0)?.alignment
        val verticalAlignment: String? = dta.templateBody?.templateLines?.get(position)?.columns?.get(0)?.verticalAlignment
        val contentType: String? = dta.templateBody?.templateLines?.get(position)?.columns?.get(0)?.contentType
        when(contentType){
              "text"-> getParameterText(view,position,percentWidth,height,alignment,verticalAlignment)
              "title"->getParameterTitle(view,position,percentWidth,height,alignment,verticalAlignment)
//            "qrcode" -> getPropertyForColumn(view) // chua xu ly
//            "image"-> getPropertyForColumn(view ) // chua xu ly
//            "imagelist" -> getPropertyForColumn(view) // chua xu ly
        }
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

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun getParameterText(viewGroup: ViewGroup, position: Int, percentWidth: Int?, height: Int?, alignment: String?, verticalAlignment: String?) {
        val text : TextView = TextView(context)
        val param : ViewGroup.LayoutParams = ViewGroup.LayoutParams(setPercenwidth(percentWidth) ,setHeight(height))
        text.layoutParams = param
        setAlignment(text,dta.templateBody?.templateLines?.get(position)?.columns?.get(0)?.alignment)
        text.text = dta.templateBody?.templateLines?.get(position)?.columns?.get(0)?.parameter?.text
        text.setTextColor(Color.parseColor(dta.templateBody?.templateLines?.get(position)?.columns?.get(0)?.parameter?.fontColor))
        setFontStyle(text,dta.templateBody?.templateLines?.get(position)?.columns?.get(0)?.parameter?.fontStyle)
        text.setTextSize(15F)
        viewGroup.addView(text)
    }


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun getParameterTitle(viewGroup: ViewGroup, position: Int, percentWidth: Int?, height: Int?, alignment: String?, verticalAlignment: String?) {

        val linearLayoutParent : LinearLayout = LinearLayout(context)
        linearLayoutParent.layoutParams = LinearLayout.LayoutParams(setPercenwidth(percentWidth),setHeight(height), 1F)
        linearLayoutParent.orientation = LinearLayout.HORIZONTAL

        val linearLayoutChild : LinearLayout = LinearLayout(context)
        linearLayoutChild.layoutParams = LinearLayout.LayoutParams(setPercenwidth(percentWidth),setHeight(height),1F)
        linearLayoutChild.orientation = LinearLayout.VERTICAL


        val txt1 : TextView = TextView(context)
        txt1.layoutParams =  linearLayoutChild.layoutParams
        txt1.setText(dta.templateBody?.templateLines?.get(position)?.columns?.get(0)?.parameter?.title)
        txt1.setTextSize(15f)
        setAlignment(txt1,"left")
        linearLayoutChild.addView(txt1)

        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm")
        val today = formatter.format(date)

       Log.e("width",""+ Resources.getSystem().getDisplayMetrics().widthPixels * 10/100)
        Log.e("height",""+ Resources.getSystem().getDisplayMetrics().heightPixels *10/100)


        val txt2 : TextView = TextView(context)
        txt2.layoutParams =  linearLayoutParent.layoutParams
        txt2.setText(today)
        txt2.setTextSize(15f)
        setAlignment(txt2,"left")
        linearLayoutChild.addView(txt2)

        getParameterImage(linearLayoutParent,0,percentWidth,height,"left",verticalAlignment)
        linearLayoutParent.addView(linearLayoutChild)
        viewGroup.addView(linearLayoutParent)
    }

    override fun getParameterImage(viewGroup: ViewGroup, position: Int, percentWidth: Int?, height: Int?, alignment: String?, verticalAlignment: String?) {
        val image : ImageView = ImageView(context)
        val param : ViewGroup.LayoutParams = ViewGroup.LayoutParams(150, 150) // temp
        image.layoutParams = param
        setAlignment(image,alignment)
        Glide.with(context).load(R.mipmap.ic_launcher_round ).into(image)
        viewGroup.addView(image)
    }
}