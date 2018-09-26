package com.training.tinhla.training.nkhoi_srcollview.presenterImpl

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.training.tinhla.training.R
import com.training.tinhla.training.nkhoi_srcollview.model.Data
import com.training.tinhla.training.nkhoi_srcollview.model.ReadJson
import com.training.tinhla.training.nkhoi_srcollview.presenterInterface.JsonInterfaceIframe

open class JsonPresenterIframeImpl(var context: Context) : JsonInterfaceIframe.presenterJson {

    private val dta : Data
    private val readJson : ReadJson
    private val  textjson : String
    init {
        readJson = ReadJson(context)
        textjson = readJson.readFileJsonFromAsset(context)
        dta = readJson.parseJsonInData()
    }

    override fun getListImageViewPager() : List<String>? {
        return dta.templateBody?.iframeProperty?.images
    }
         // Iframe
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun getPropertyForColumnIframe(view: ViewGroup) {
        for (i in 0..(dta.templateBody?.iframeProperty?.templateLines?.size!! - 1) step 1) {
            val percentWidth: Int? = dta.templateBody?.iframeProperty?.templateLines?.get(i)?.columns?.get(0)?.percentWidth
            val height: Int? = dta.templateBody?.iframeProperty?.templateLines?.get(i)?.columns?.get(0)?.height
            val alignment: String? = dta.templateBody?.iframeProperty?.templateLines?.get(i)?.columns?.get(0)?.alignment
            val verticalAlignment: String? = dta.templateBody?.iframeProperty?.templateLines?.get(i)?.columns?.get(0)?.verticalAlignment
            val contentType: String? = dta.templateBody?.iframeProperty?.templateLines?.get(i)?.columns?.get(0)?.contentType
            when (contentType) {
                "image" -> getParameterIcon(view, i, percentWidth, height, alignment, verticalAlignment)
                "text" -> getParameterText(view, i,0, percentWidth, height, alignment, verticalAlignment)
            }
        }
    }

    override fun getParameterIcon(viewGroup: ViewGroup, positionTemplateLine: Int, percentWidth: Int?, height: Int?, alignment: String?, verticalAlignment: String?) {
        val linearLayout: LinearLayout = LinearLayout(context)
        val paramlinear = LinearLayout.LayoutParams(setPercenwidth(percentWidth),setHeight(height))
        linearLayout.layoutParams = paramlinear
        linearLayout.orientation = LinearLayout.HORIZONTAL
        setAlignment(linearLayout,alignment)
        val image :ImageView = ImageView(context)
        val param : ViewGroup.LayoutParams = ViewGroup.LayoutParams(150,150)
        image.layoutParams = param
        image.scaleType = ImageView.ScaleType.FIT_START
        val url : String?  = dta.templateBody?.iframeProperty?.templateLines?.get(positionTemplateLine)?.columns?.get(0)?.parameter?.url
        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.mipmap.ic_launcher)
        Glide.with(context).setDefaultRequestOptions(requestOptions).load(url).into(image);
        linearLayout.addView(image)
        viewGroup.addView(linearLayout)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun getParameterText(viewGroup: ViewGroup, positionTemplateLine: Int,positionColumn: Int, percentWidth: Int?, height: Int?, alignment: String?, verticalAlignment: String?) {
        val linearLayout: LinearLayout = LinearLayout(context)
        val paramlinear = LinearLayout.LayoutParams(setPercenwidth(percentWidth),setHeight(height))
        linearLayout.layoutParams = paramlinear
        linearLayout.orientation = LinearLayout.HORIZONTAL
        setAlignment(linearLayout,alignment)
        val text :TextView = TextView(context)
        val param : ViewGroup.LayoutParams = ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT ,LinearLayout.LayoutParams.WRAP_CONTENT )
        text.layoutParams = param
        text.text = dta.templateBody?.iframeProperty?.templateLines?.get(positionTemplateLine)?.columns?.get(0)?.parameter?.text
        text.setTextColor(Color.parseColor(dta.templateBody?.iframeProperty?.templateLines?.get(positionTemplateLine)?.columns?.get(0)?.parameter?.fontColor))
        setFontStyle(text,dta.templateBody?.iframeProperty?.templateLines?.get(positionTemplateLine)?.columns?.get(0)?.parameter?.fontStyle)
        setFontSize(text,dta.templateBody?.iframeProperty?.templateLines?.get(positionTemplateLine)?.columns?.get(0)?.parameter?.fontSize)
        linearLayout.addView(text)
        viewGroup.addView(linearLayout)
    }


    /////////////////////// public

   override fun setPercenwidth(percen: Int?) :Int {
       if (percen!!<10)percen ==10
       if (percen>90) percen ==90
       val i: Int = when(percen){
            100 -> ViewGroup.LayoutParams.MATCH_PARENT
            0 ->ViewGroup.LayoutParams.WRAP_CONTENT
           else ->  (Resources.getSystem().getDisplayMetrics().widthPixels -150)* percen /100 // except margin left right 50 , get size screen
        }
        return i
    }

    override fun setHeight(height: Int?) :Int {
      val i : Int = when(height){
            0 -> ViewGroup.LayoutParams.WRAP_CONTENT
            else -> height!!
        }
        return i
    }

    @SuppressLint("RtlHardcoded")
    override fun setAlignment(linearLayout: LinearLayout, alignment: String?) {
        when(alignment){
            "left" ->   linearLayout.gravity = Gravity.LEFT
            "center" -> linearLayout.gravity = Gravity.CENTER
            "right" ->  linearLayout.gravity = Gravity.RIGHT
        }
    }

    override fun setFontStyle(textView: TextView, fontStyle: String?){
        when (fontStyle){
            "bold"->textView.setTypeface(null,Typeface.BOLD)
            "bold_italic"->textView.setTypeface(null,Typeface.BOLD_ITALIC)
            "italic"->textView.setTypeface(null,Typeface.ITALIC)
            "normal"->textView.setTypeface(null,Typeface.NORMAL)
        }

    }

    override fun setFontSize(textView: TextView, fontSize: String?) {
        when (fontSize){
            "medium"->textView.textSize = 13F
            "large"->textView.textSize = 15F
            "verylarge"->textView.textSize = 20F
        }
    }
}