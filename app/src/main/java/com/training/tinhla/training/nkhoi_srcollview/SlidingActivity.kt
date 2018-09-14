package com.training.tinhla.training.nkhoi_srcollview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.training.tinhla.training.R
import kotlinx.android.synthetic.main.activity_main_sliding.*

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
    }

    override fun LoadTextFail(text: String) {
        tvDetail.setText(text)
    }
}
