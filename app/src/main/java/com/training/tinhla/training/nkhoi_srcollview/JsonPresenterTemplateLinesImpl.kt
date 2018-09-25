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
import com.bumptech.glide.request.RequestOptions
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
                "normal"-> setTypeLine(viewGroup, i) // i is position of templateLine
                "emptyLine"->setTypeLineEmptyLine(viewGroup)
                "drawLine"->setTypeLineDrawLine(viewGroup)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun setTypeLine(view: ViewGroup , position: Int) {
        val numberColumn: Int? = dta.templateBody?.templateLines?.get(position)?.columns?.size
        if(numberColumn!! > 1) setTypeLineTwoColum(view,position) else setTypeLineOneColumn(view,position)
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
              "text"-> getParameterText(view,position,0,percentWidth,height,alignment,verticalAlignment)
              "title"->getParameterTitle(view,position,percentWidth,height,alignment,verticalAlignment)
              "image" -> getParameterImage(view,position,0,percentWidth,height,alignment,verticalAlignment)
              "imagelist" -> getParameterListImage(view,position,0,percentWidth,height,alignment,verticalAlignment)
        }
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun setTypeLineTwoColum(view: ViewGroup, position: Int) {
        val param : LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,1f )
        val linearLayout : LinearLayout = LinearLayout(context)
        linearLayout.layoutParams = param
        linearLayout.orientation=LinearLayout.HORIZONTAL
        for (i in 0..(dta.templateBody?.templateLines?.get(position)?.columns?.size!!-1) step 1){
            val percentWidth: Int? = dta.templateBody?.templateLines?.get(position)?.columns?.get(i)?.percentWidth
            val height: Int? = dta.templateBody?.templateLines?.get(position)?.columns?.get(i)?.height
            val alignment: String? = dta.templateBody?.templateLines?.get(position)?.columns?.get(i)?.alignment
            val verticalAlignment: String? = dta.templateBody?.templateLines?.get(position)?.columns?.get(i)?.verticalAlignment
            val contentType: String? = dta.templateBody?.templateLines?.get(position)?.columns?.get(i)?.contentType
            when(contentType){
                "text"-> getParameterText(linearLayout,position,i,percentWidth,height,alignment,verticalAlignment) // i is position of column
                "qrcode"-> getParameterImage(linearLayout,position,i,percentWidth,height,alignment,verticalAlignment)
                "image"->getParameterImage(linearLayout,position,i,percentWidth,height,alignment,verticalAlignment)
            }
        }
        view.addView(linearLayout)
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
    override fun getParameterText(viewGroup: ViewGroup, positionTempalteLine: Int, positionColumn: Int, percentWidth: Int?, height: Int?, alignment: String?, verticalAlignment: String?) {
        val text : TextView = TextView(context)
        val param : ViewGroup.LayoutParams = ViewGroup.LayoutParams(setPercenwidth(percentWidth) ,setHeight(height))
        text.layoutParams = param
        setAlignment(text,dta.templateBody?.templateLines?.get(positionTempalteLine)?.columns?.get(positionColumn)?.alignment)
        text.text = dta.templateBody?.templateLines?.get(positionTempalteLine)?.columns?.get(positionColumn)?.parameter?.text
        text.setTextColor(Color.parseColor(dta.templateBody?.templateLines?.get(positionTempalteLine)?.columns?.get(positionColumn)?.parameter?.fontColor))
        setFontStyle(text,dta.templateBody?.templateLines?.get(positionTempalteLine)?.columns?.get(positionColumn)?.parameter?.fontStyle)
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


        val title : TextView = TextView(context)
        title.layoutParams =  linearLayoutChild.layoutParams
        title.setText(dta.templateBody?.templateLines?.get(position)?.columns?.get(0)?.parameter?.title)
        title.setTextSize(15f)
        setAlignment(title,"left")
        linearLayoutChild.addView(title)

        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm")
        val today = formatter.format(date)

        val timeStamp : TextView = TextView(context)
        timeStamp.layoutParams =  linearLayoutParent.layoutParams
        timeStamp.setText(today)
        timeStamp.setTextSize(15f)
        setAlignment(timeStamp,"left")
        linearLayoutChild.addView(timeStamp)

        getParameterIcon(linearLayoutParent,0,percentWidth,height,"left",verticalAlignment)
        linearLayoutParent.addView(linearLayoutChild)
        viewGroup.addView(linearLayoutParent)
    }

    override fun getParameterIcon(viewGroup: ViewGroup, position: Int, percentWidth: Int?, height: Int?, alignment: String?, verticalAlignment: String?) {
        val image : ImageView = ImageView(context)
        val param : ViewGroup.LayoutParams = ViewGroup.LayoutParams(150, setHeight(height)) // temp
        image.layoutParams = param
        setAlignment(image,alignment)
        Glide.with(context).load(R.mipmap.ic_launcher_round ).into(image)
        viewGroup.addView(image)
    }

    override fun getParameterImage(viewGroup: ViewGroup, positionTempalteLine: Int, positionColumn: Int, percentWidth: Int?, height: Int?, alignment: String?, verticalAlignment: String?) {
        val image : ImageView = ImageView(context)
        val url = dta.templateBody?.templateLines?.get(positionTempalteLine)?.columns?.get(positionColumn)?.parameter?.url
        val param : ViewGroup.LayoutParams = ViewGroup.LayoutParams(setPercenwidth(percentWidth), setHeight(height))
        image.layoutParams = param
        setAlignment(image,alignment)
        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.drawable.bg_default)
        Glide.with(context)
                .setDefaultRequestOptions(requestOptions)
                .load(url).into(image);
        viewGroup.addView(image)
    }
    override fun getParameterListImage(viewGroup: ViewGroup, positionTeamplate: Int, positionColumn: Int, percentWidth: Int?, height: Int?, alignment: String?, verticalAlignment: String?) {

    }
}