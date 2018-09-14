package com.training.tinhla.training.nkhoi_srcollview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.training.tinhla.training.R
import kotlinx.android.synthetic.main.activity_main_sliding.*

class SlidingActivity : AppCompatActivity(),InterfaceSlidingActivity {
    private var presenterSliding : PresenterSliding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_sliding)
        presenterSliding = PresenterSliding(this)
        presenterSliding?.getDBlistText()
    }

    override fun LoadTextSuccess(text : String) {
        tvDetail.setText(text)
    }

    override fun LoadTextFail(text: String) {
        tvDetail.setText(text)
    }

//    app:layout_constraintTop_toTopOf="parent"
//    app:layout_constraintStart_toStartOf="parent"
//    app:layout_constraintEnd_toEndOf="parent"
}
