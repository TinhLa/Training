package com.training.tinhla.training.nkhoi_srcollview

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.view.ViewPager
import android.util.Log
import com.training.tinhla.training.R
import com.training.tinhla.training.nkhoi_srcollview.adapter.ViewPagerAdapter
import com.training.tinhla.training.nkhoi_srcollview.model.TemplateButton
import kotlinx.android.synthetic.main.activity_main_sliding.*
import kotlinx.android.synthetic.main.include_ifarme_property.*
import kotlinx.android.synthetic.main.include_viewpager.*


class SlidingActivity : AppCompatActivity(),SlidingInterface.viewSliding {
    private lateinit var presenterSliding : SlidingPresenterImpl
    private lateinit var presenterIframe : JsonPresenterIframeImpl
    private lateinit var presenterTemplateLines: JsonPresenterTemplateLinesImpl
    var array2 : List<Int> = listOf(R.drawable.ntkh_image7,R.drawable.ntkn_image6,R.drawable.ntkn_image5 , R.drawable.ntkn_image4)
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_sliding)
        presenterSliding = SlidingPresenterImpl(this)

        presenterIframe = JsonPresenterIframeImpl(this)
        presenterTemplateLines = JsonPresenterTemplateLinesImpl(this)
        presenterIframe.getPropertyForColumnIframe(column_iframe)
        presenterTemplateLines.getPropertyForColumnTemplateLines(template_lines)
        presenterSliding.view.loadTextSuccess("")
    }

    override fun loadTextSuccess(text : String) {
        val myadapter = ViewPagerAdapter(this,  listOf("1","2","3","4")) // test UI   -> presenterIframe.getListImageViewPager())
        viewpager_image.setAdapter(myadapter)
        presenterSliding.creatDotsOfViewPager(this,dots,listOf("1","2","3","4").size) // -> if(presenterIframe.getListImageViewPager()===null) 1 else presenterIframe.getListImageViewPager()!!.size
        viewpager_image.addOnPageChangeListener(onPageChangeListener)

    }

    override fun loadTextFail(text: String) {

    }

    val onPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
            presenterSliding.onPageChangeViewPager(applicationContext,
                                                  // if(presenterIframe.getListImageViewPager()===null) 1 else presenterIframe.getListImageViewPager()!!.size,
                                                    listOf("1","2","3","4").size,
                                                    position,dots)
        }

        override fun onPageScrollStateChanged(state: Int) {
        }
    }
}
