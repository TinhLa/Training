package com.training.tinhla.training.nkhoi_srcollview.presenterImpl

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.view.ViewPager
import android.view.Gravity
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.training.tinhla.training.R
import com.training.tinhla.training.nkhoi_srcollview.adapter.ViewPagerAdapter
import com.training.tinhla.training.nkhoi_srcollview.model.Data
import com.training.tinhla.training.nkhoi_srcollview.model.ReadJson
import com.training.tinhla.training.nkhoi_srcollview.presenterInterface.SlidingInterface
import java.util.*
import java.text.SimpleDateFormat


class JsonPresenterTemplateLinesImpl(view : SlidingInterface.viewSliding, context: Context) : JsonPresenterIframeImpl(context) {

    private val dta : Data
    private val readJson : ReadJson
    private val  textjson : String
    private val slidingPresenterImpl : SlidingPresenterImpl

    init {
        readJson = ReadJson(context)
        textjson = readJson.readFileJsonFromAsset(context)
        dta = readJson.parseJsonInData()
        slidingPresenterImpl = SlidingPresenterImpl(view)
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
              "image" -> getParameterImageOneColumn(view,position,0,percentWidth,height,alignment,verticalAlignment)
              "imagelist" -> getParameterListImage(view,position,0,percentWidth,height,alignment,verticalAlignment)
              "qrcode"-> getParameterImage(view,position,0,percentWidth,height,alignment,verticalAlignment)
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
                "imagelist" -> getParameterListImage(view,position,i,percentWidth,height,alignment,verticalAlignment)
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
    override fun getParameterText(viewGroup: ViewGroup, positionTemplateLine: Int, positionColumn: Int, percentWidth: Int?, height: Int?, alignment: String?, verticalAlignment: String?) {
        val linearLayout: LinearLayout = LinearLayout(context)
        val paramlinear = LinearLayout.LayoutParams(setPercenwidth(percentWidth),setHeight(height))
        linearLayout.layoutParams = paramlinear
        linearLayout.orientation = LinearLayout.HORIZONTAL
        setAlignment(linearLayout,alignment)
        val text : TextView = TextView(context)
        val param : ViewGroup.LayoutParams = ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT ,LinearLayout.LayoutParams.WRAP_CONTENT )
        text.layoutParams = param
        text.text = dta.templateBody?.templateLines?.get(positionTemplateLine)?.columns?.get(positionColumn)?.parameter?.text
        text.setTextColor(Color.parseColor(dta.templateBody?.templateLines?.get(positionTemplateLine)?.columns?.get(positionColumn)?.parameter?.fontColor))
        setFontStyle(text,dta.templateBody?.templateLines?.get(positionTemplateLine)?.columns?.get(positionColumn)?.parameter?.fontStyle)
        setFontSize(text,dta.templateBody?.templateLines?.get(positionTemplateLine)?.columns?.get(positionColumn)?.parameter?.fontSize)
        linearLayout.addView(text)
        viewGroup.addView(linearLayout)
    }


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun getParameterTitle(viewGroup: ViewGroup, positionTemplateLine: Int, percentWidth: Int?, height: Int?, alignment: String?, verticalAlignment: String?) {
        val linearLayout: LinearLayout = LinearLayout(context)
        val paramlinear = LinearLayout.LayoutParams(setPercenwidth(percentWidth),setHeight(height))
        linearLayout.layoutParams = paramlinear
        linearLayout.orientation = LinearLayout.HORIZONTAL
        setAlignment(linearLayout,alignment)

        val linearLayoutParent : LinearLayout = LinearLayout(context)
        linearLayoutParent.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT, 1F)
        linearLayoutParent.orientation = LinearLayout.HORIZONTAL

        val linearLayoutChild : LinearLayout = LinearLayout(context)
        linearLayoutChild.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
        linearLayoutChild.orientation = LinearLayout.VERTICAL

        // set icon
        getParameterIcon(linearLayoutParent,0,percentWidth,height,"left",verticalAlignment)

        // set title
        val title : TextView = TextView(context)
        title.layoutParams =  linearLayoutChild.layoutParams
        title.setText(dta.templateBody?.templateLines?.get(positionTemplateLine)?.columns?.get(0)?.parameter?.title)
        setFontSize(title,dta.templateBody?.templateLines?.get(positionTemplateLine)?.columns?.get(0)?.parameter?.fontSize)
        title.textAlignment = TextView.TEXT_ALIGNMENT_VIEW_START
        linearLayoutChild.addView(title)

        // set time
        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm")
        val today = formatter.format(date)
        val timeStamp : TextView = TextView(context)
        timeStamp.layoutParams =  linearLayoutChild.layoutParams
        timeStamp.setText(today)
        setFontSize(title,dta.templateBody?.templateLines?.get(positionTemplateLine)?.columns?.get(0)?.parameter?.fontSize)
        timeStamp.textAlignment = TextView.TEXT_ALIGNMENT_VIEW_START
        linearLayoutChild.addView(timeStamp)

        linearLayoutParent.addView(linearLayoutChild)
        linearLayout.addView(linearLayoutParent)
        viewGroup.addView(linearLayout)
    }

    override fun getParameterIcon(viewGroup: ViewGroup, positionTemplateLine: Int, percentWidth: Int?, height: Int?, alignment: String?, verticalAlignment: String?) {
        val image : ImageView = ImageView(context)
        val param : ViewGroup.LayoutParams = ViewGroup.LayoutParams(150, 150)
        image.layoutParams = param
        val url : String?  = dta.templateBody?.templateLines?.get(positionTemplateLine)?.columns?.get(0)?.parameter?.url
        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.mipmap.ic_launcher)
        Glide.with(context).setDefaultRequestOptions(requestOptions).load(url).into(image);
        viewGroup.addView(image)
    }

    override fun getParameterImage(viewGroup: ViewGroup, positionTemplateLine: Int, positionColumn: Int, percentWidth: Int?, height: Int?, alignment: String?, verticalAlignment: String?) {
        val linearLayout: LinearLayout = LinearLayout(context)
        val paramlinear = LinearLayout.LayoutParams(setPercenwidth(percentWidth),setHeight(height))
        linearLayout.layoutParams = paramlinear
        linearLayout.orientation = LinearLayout.HORIZONTAL
        setAlignment(linearLayout,alignment)

        val image : ImageView = ImageView(context)
        val url = dta.templateBody?.templateLines?.get(positionTemplateLine)?.columns?.get(positionColumn)?.parameter?.url
        val param : ViewGroup.LayoutParams = ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, setHeight(height))
        image.layoutParams = param
        image.scaleType = ImageView.ScaleType.FIT_CENTER
        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.drawable.ntkn_image4)
        Glide.with(context).setDefaultRequestOptions(requestOptions).load(url).into(image);
        linearLayout.addView(image)
        viewGroup.addView(linearLayout)
    }

    override fun getParameterImageOneColumn(viewGroup: ViewGroup, positionTemplateLine: Int, positionColumn: Int, percentWidth: Int?, height: Int?, alignment: String?, verticalAlignment: String?) {
        val linearLayout: LinearLayout = LinearLayout(context)
        val paramlinear = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,setHeight(height))
        linearLayout.layoutParams = paramlinear
        linearLayout.orientation = LinearLayout.HORIZONTAL
        setAlignment(linearLayout,alignment)
        val image : ImageView = ImageView(context)
        val url = dta.templateBody?.templateLines?.get(positionTemplateLine)?.columns?.get(positionColumn)?.parameter?.url
        val param : ViewGroup.LayoutParams = ViewGroup.LayoutParams(setPercenwidth(percentWidth), setHeight(height))
        image.layoutParams = param
        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.drawable.ntkn_image4)
        Glide.with(context).setDefaultRequestOptions(requestOptions).load(url).into(image);
        linearLayout.addView(image)
        viewGroup.addView(linearLayout)
    }
    override fun getParameterListImage(viewGroup: ViewGroup, positionTemplateLine: Int, positionColumn: Int, percentWidth: Int?, height: Int?, alignment: String?, verticalAlignment: String?) {
        val param : ViewGroup.LayoutParams = ViewGroup.LayoutParams(setPercenwidth(percentWidth), setHeight(height))
        val frameLayout : FrameLayout = FrameLayout(context)
        frameLayout.layoutParams = param
        val viewpager : ViewPager = ViewPager(context)
        viewpager.layoutParams = param
        val myadapter = ViewPagerAdapter(context,  listOf("1","2","3","4"))
        viewpager.adapter = myadapter
        val linearLayout : LinearLayout = LinearLayout(context)
        val paramDots : LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,1f)
        paramDots.setMargins(0, 0,0,0) // add dots on top list image
        linearLayout.orientation = LinearLayout.HORIZONTAL
        linearLayout.layoutParams = paramDots
        linearLayout.gravity = Gravity.CENTER_HORIZONTAL
        slidingPresenterImpl.creatDotsOfViewPager(context,linearLayout,4)
        viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }
            override fun onPageSelected(position: Int) {
                slidingPresenterImpl.onPageChangeViewPager(context,
                        listOf("1","2","3","4").size,
                        position,linearLayout)
            }
            override fun onPageScrollStateChanged(state: Int) {
            }
        })
        frameLayout.addView(viewpager)
        frameLayout.addView(linearLayout)
        viewGroup.addView(frameLayout)
    }
}