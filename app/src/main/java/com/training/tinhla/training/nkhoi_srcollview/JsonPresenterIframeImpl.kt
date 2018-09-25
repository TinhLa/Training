package com.training.tinhla.training.nkhoi_srcollview

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.training.tinhla.training.R
import com.training.tinhla.training.nkhoi_srcollview.model.Data
import com.training.tinhla.training.nkhoi_srcollview.model.ReadJson

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

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun getPropertyForColumnIframe(view: ViewGroup, position: Int) {
        for (i in 0..(dta.templateBody?.iframeProperty?.templateLines?.size!! - 1) step 1) {
            val percentWidth: Int? = dta.templateBody?.iframeProperty?.templateLines?.get(i)?.columns?.get(0)?.percentWidth
            val height: Int? = dta.templateBody?.iframeProperty?.templateLines?.get(i)?.columns?.get(0)?.height
            val alignment: String? = dta.templateBody?.iframeProperty?.templateLines?.get(i)?.columns?.get(0)?.alignment
            val verticalAlignment: String? = dta.templateBody?.iframeProperty?.templateLines?.get(i)?.columns?.get(0)?.verticalAlignment
            val contentType: String? = dta.templateBody?.iframeProperty?.templateLines?.get(i)?.columns?.get(0)?.contentType
            when (contentType) {
                "image" -> getParameterImage(view, i, percentWidth, height, alignment, verticalAlignment)
                "text" -> getParameterText(view, i, percentWidth, height, alignment, verticalAlignment)
            }
        }
    }

    ///////////////////////////////////////////////////////////// public

    override fun getParameterImage(viewGroup: ViewGroup,position: Int, percentWidth: Int?, height: Int?, alignment: String?, verticalAlignment: String?) {
        val image :ImageView = ImageView(context)
        val param : ViewGroup.LayoutParams = ViewGroup.LayoutParams(setPercenwidth(percentWidth) , 100) // temp
        image.layoutParams = param
        setAlignment(image,alignment)
      //  val url : String?  = dta.templateBody?.iframeProperty?.templateLines?.get(position)?.columns?.get(0)?.parameter?.url
     //   Glide.with(context).load(url).into(image)
        Glide.with(context).load(R.mipmap.ic_launcher_round ).into(image)
        viewGroup.addView(image)
    }


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun getParameterText(viewGroup: ViewGroup, position: Int, percentWidth: Int?, height: Int?, alignment: String?, verticalAlignment: String?) {
        var text :TextView = TextView(context)
        val param : ViewGroup.LayoutParams = ViewGroup.LayoutParams(setPercenwidth(percentWidth) ,setHeight(height))
        text.layoutParams = param
        setAlignment(text,dta.templateBody?.iframeProperty?.templateLines?.get(position)?.columns?.get(0)?.alignment)
        text.text = dta.templateBody?.iframeProperty?.templateLines?.get(position)?.columns?.get(0)?.parameter?.text
        text.setTextColor(Color.parseColor(dta.templateBody?.iframeProperty?.templateLines?.get(position)?.columns?.get(0)?.parameter?.fontColor))
        setFontStyle(text,dta.templateBody?.iframeProperty?.templateLines?.get(position)?.columns?.get(0)?.parameter?.fontStyle)
        text.setBackgroundColor(Color.parseColor("#000000"))
        text.setTextSize(20F)
        viewGroup.addView(text)
    }


   override fun setPercenwidth(percen: Int?) :Int {
       val i: Int = when(percen){
            100 -> ViewGroup.LayoutParams.MATCH_PARENT
            else ->ViewGroup.LayoutParams.WRAP_CONTENT
        }
        return i
    }

    override fun setHeight(height: Int?) :Int {
      val i : Int = when(height){
            0 -> ViewGroup.LayoutParams.WRAP_CONTENT
            null -> ViewGroup.LayoutParams.WRAP_CONTENT
            else -> height
        }
        return i
    }

    override fun setAlignment(imageView: ImageView, alignment: String?){
        when(alignment){
            "left" ->   imageView.scaleType = ImageView.ScaleType.FIT_START
            "center" -> imageView.scaleType = ImageView.ScaleType.FIT_CENTER
            "right" ->  imageView.scaleType = ImageView.ScaleType.FIT_END
        }
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun setAlignment(textView: TextView, alignment: String?){
        when(alignment){
            "left" ->   textView.textAlignment =TextView.TEXT_ALIGNMENT_TEXT_START
            "center" -> textView.textAlignment =TextView.TEXT_ALIGNMENT_TEXT_END
            "right" ->  textView.textAlignment =TextView.TEXT_ALIGNMENT_CENTER
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

    ////////////////////////////////////////////////////// for Template Lines Body

    override fun getPropertyForColumnTemplateLines(viewGroup: ViewGroup) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun setTypeLine(view: ViewGroup, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setTypeLineOneColumn(view: ViewGroup, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setTypeLineTwoColum(view: ViewGroup, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setTypeLineDrawLine(view: ViewGroup) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setTypeLineEmptyLine(view: ViewGroup) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun getParameterTitle(viewGroup: ViewGroup, position: Int, percentWidth: Int?, height: Int?, alignment: String?, verticalAlignment: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}