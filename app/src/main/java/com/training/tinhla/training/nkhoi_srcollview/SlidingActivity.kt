package com.training.tinhla.training.nkhoi_srcollview


import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import com.training.tinhla.training.R
import com.training.tinhla.training.nkhoi_srcollview.adapter.ViewPagerAdapter
import com.training.tinhla.training.nkhoi_srcollview.model.Data
import com.training.tinhla.training.nkhoi_srcollview.model.TemplateButton
import kotlinx.android.synthetic.main.activity_main_sliding.*
import kotlinx.android.synthetic.main.include_ifarme_property.*
import kotlinx.android.synthetic.main.include_viewpager.*


class SlidingActivity : AppCompatActivity(),SlidingInterface.viewSliding {
    private lateinit var presenterSliding : SlidingPresenterImpl
    private lateinit var presenterJson : JsonPresenterImpl
    private lateinit var arrayList: ArrayList<TemplateButton>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_sliding)
        presenterSliding = SlidingPresenterImpl(this)
        presenterJson = JsonPresenterImpl(this,this)
        presenterJson.getDBlistText()
//        presenterJson.getPropertyForColumnIframe(0)
//        val layoutParams = img_icon_property.layoutParams
//        text1_ifarme_property.setTextColor(Color.)
    }

//    fun checkCondision(){
//        val data = Data()
//        Color.
//        val width = data.templateBody.getIframeProperty().getTemplateLines().first().getColumns().first().getColumnPercentWidth()
//        when (width){
//            100 ->{
//
//            }
//            else ->{
//
//            }
//        }
//    }

    override fun loadTextSuccess(text : String) {
        val myadapter = ViewPagerAdapter(this, presenterJson.getListImageViewPager())
        viewpager_image.setAdapter(myadapter)
        presenterSliding.creatDotsOfViewPager(this,dots,presenterJson.getListImageViewPager().size)
        viewpager_image.addOnPageChangeListener(onPageChangeListener)
        tvDetail.setText(text)
    }

    override fun loadTextFail(text: String) {
        tvDetail.setText(text)
    }

    val onPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
            presenterSliding.onPageChangeViewPager(applicationContext,presenterJson.getListImageViewPager().size,position,dots)
        }

        override fun onPageScrollStateChanged(state: Int) {
        }
    }
}
