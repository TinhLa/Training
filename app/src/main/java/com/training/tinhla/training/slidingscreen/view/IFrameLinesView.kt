package com.training.tinhla.training.slidingscreen.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.training.tinhla.training.commonmodel.templatebody.templateLines.ColumnsLineTemplate
import com.training.tinhla.training.commonmodel.templatebody.templateLines.TemplateLines

class IFrameLinesView @JvmOverloads constructor(context: Context, att: AttributeSet? = null, def: Int = 0) : LinearLayout(context, att, def) {

    fun createView(dataIframe : List<TemplateLines>){
        for (i in 0 until dataIframe.size){
            when(dataIframe[i].lineType){
                "normal"->{if (dataIframe[i].columns[0].contentType == "text") addTextLine(dataIframe[i].columns)
                if (dataIframe[i].columns[0].contentType == "titlenormal") addTitle(dataIframe[i].columns)}
                "emptyLine" -> addEmptyLine()
                "drawLine" -> addDrawLine()
            }
        }
    }

    private fun addEmptyLine() {
        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 20)
        params.setMargins(0, 20, 0, 20)
        val line = LinearLayout(this.context)
        line.layoutParams = params
        this.addView(line)
    }

    private fun addDrawLine() {
        val paramsFrame = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 20)
        paramsFrame.setMargins(0, 20, 0, 20)
        val lineFrame = LinearLayout(this.context)
        lineFrame.layoutParams = paramsFrame
        val paramsLine = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 5)
        val line = View(this.context)
        line.setBackgroundColor(Color.BLACK)
        line.layoutParams = paramsLine
        lineFrame.addView(line)
        this.addView(lineFrame)
    }
    private fun addTitle(columns: List<ColumnsLineTemplate>){
        val paramsFrame = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        paramsFrame.setMargins(0, 20, 0, 20)
        val lineFrame = LinearLayout(this.context)
        lineFrame.layoutParams = paramsFrame
        lineFrame.orientation = LinearLayout.HORIZONTAL
        lineFrame.setBackgroundColor(Color.parseColor(columns[0].parameter.backgroundColor))
        //icon
        val paramImg = LinearLayout.LayoutParams(100,100)
        val icon = ImageView(this.context)
        Glide.with(this.context).load(columns[0].parameter.icon).into(icon)
        icon.layoutParams =paramImg
        lineFrame.addView(icon)
        //text
        val paramText = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT )
        val text = TextView(this.context)
        paramText.setMargins(10,10,10,10)
        text.setTextColor(Color.parseColor(columns[0].parameter.timeStampFontColor))
        text.layoutParams = paramText
        text.text = columns[0].parameter.title
        lineFrame.addView(text)

        this.addView(lineFrame)

    }
    private fun addTextLine(columns: List<ColumnsLineTemplate>){
        val metrics = this.context.resources.displayMetrics
        val paramsFrame = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        paramsFrame.setMargins(0, 20, 0, 20)
        paramsFrame.width = (metrics.widthPixels*0.9).toInt()
        val lineFrame = LinearLayout(this.context)
        lineFrame.layoutParams = paramsFrame
        lineFrame.orientation = LinearLayout.HORIZONTAL
        for(i in 0 until columns.size){
            val paramText = LinearLayout.LayoutParams(metrics.widthPixels*4/10,LinearLayout.LayoutParams.WRAP_CONTENT )
            val text = TextView(this.context)
            paramText.setMargins(10,10,10,10)
            text.setTextColor(Color.parseColor(columns[i].parameter.fontColor))
            paramText.weight = columns[i].height.toFloat()
            when (columns[i].alignment) {
                "center" -> text.gravity = Gravity.CENTER_HORIZONTAL
                "left" -> text.gravity = Gravity.START
                "right" -> text.gravity = Gravity.END
            }
            text.layoutParams = paramText
            text.text = columns[i].parameter.text
            lineFrame.addView(text)
        }
        this.addView(lineFrame)
    }
}