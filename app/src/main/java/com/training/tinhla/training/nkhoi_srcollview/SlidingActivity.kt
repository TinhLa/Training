package com.training.tinhla.training.nkhoi_srcollview

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Html
import android.widget.LinearLayout
import android.widget.TextView
import com.training.tinhla.training.R
import com.training.tinhla.training.nkhoi_srcollview.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main_sliding.*
import kotlinx.android.synthetic.main.include_viewpager.*
import kotlinx.android.synthetic.main.item_image_property.*


class SlidingActivity : AppCompatActivity(),SlidingInterface.viewSliding {
  private lateinit var presenterSliding : SlidingPresenterImpl
    private lateinit var listText :List<TextView>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_sliding)
        presenterSliding = SlidingPresenterImpl(this)
        presenterSliding.getDBlistText()
    }

    override fun loadTextSuccess(text : String) {
        tvDetail.setText(text)
        val myadapter = ViewPagerAdapter(this, presenterSliding.getListImageViewPager())
        viewpager_image.setAdapter(myadapter)
        presenterSliding.creatdotsOfViewpager(this,dots,presenterSliding.getListImageViewPager().size)
    }

    override fun loadTextFail(text: String) {
        tvDetail.setText(text)
    }
}
