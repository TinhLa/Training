package com.training.tinhla.training.nkhoi_srcollview


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.util.Log
import android.widget.TextView
import com.training.tinhla.training.R
import com.training.tinhla.training.nkhoi_srcollview.adapter.ViewPagerAdapter
import com.training.tinhla.training.nkhoi_srcollview.model.TemplateButton
import kotlinx.android.synthetic.main.activity_main_sliding.*
import kotlinx.android.synthetic.main.include_viewpager.*


class SlidingActivity : AppCompatActivity(),SlidingInterface.viewSliding {
    private lateinit var presenterSliding : SlidingPresenterImpl
    private lateinit var presenterJson : JsonPresenterImpl
    private lateinit var arrayList: ArrayList<TemplateButton>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_sliding)
        presenterSliding = SlidingPresenterImpl(this)
        presenterSliding.getDBlistText()
        presenterJson = JsonPresenterImpl(this)


    }

    override fun loadTextSuccess(text : String) {
        tvDetail.setText(text)
        val myadapter = ViewPagerAdapter(this, presenterSliding.getListImageViewPager())
        viewpager_image.setAdapter(myadapter)
        presenterSliding.creatDotsOfViewPager(this,dots,presenterSliding.getListImageViewPager().size)
        viewpager_image.addOnPageChangeListener(onPageChangeListener)

    }

    override fun loadTextFail(text: String) {
        tvDetail.setText(text)
    }

        val onPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
            presenterSliding.onPageChangeViewPager(applicationContext,presenterSliding.getListImageViewPager().size,position,dots)
        }

        override fun onPageScrollStateChanged(state: Int) {
        }
    }
}
