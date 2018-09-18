package com.training.tinhla.training.nkhoi_srcollview

import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.training.tinhla.training.R
import com.training.tinhla.training.nkhoi_srcollview.adapter.recyclerAdapter
import kotlinx.android.synthetic.main.activity_main_sliding.*
import kotlinx.android.synthetic.main.include_ifarme_property.*


class SlidingActivity : AppCompatActivity(),SlidingInterface.viewSliding {
  private lateinit var presenterSliding : SlidingPresenterImpl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_sliding)
        presenterSliding = SlidingPresenterImpl(this)
        presenterSliding.getDBlistText()
    }

    override fun LoadTextSuccess(text : String) {
        tvDetail.setText(text)
        recycler_property.layoutManager = LinearLayoutManager(this , LinearLayout.HORIZONTAL , false)
        presenterSliding.loadRecycler(recycler_property, this)
    }

    override fun LoadTextFail(text: String) {
        tvDetail.setText(text)
    }
}
