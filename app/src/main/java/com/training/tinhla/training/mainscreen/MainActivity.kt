package com.training.tinhla.training.mainscreen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import com.training.tinhla.training.R
import com.training.tinhla.training.base.model.constant.ACTIVITY_ID
import com.training.tinhla.training.base.model.constant.CONSTANT
import com.training.tinhla.training.basemodel.BaseActivity
import com.training.tinhla.training.scrollscreen.ScrollActivity

class MainActivity : BaseActivity() {

    override fun getActivityID(): String {
        return ACTIVITY_ID.MAIN
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun openNextActivity(activityID: String) {
        val intent = Intent(this, ScrollActivity::class.java)
        startActivity(intent)
    }

    fun onOpenScrollScreen(view: View) {
        val intent = Intent(this, ScrollActivity::class.java)
        startActivity(intent)
    }
}
