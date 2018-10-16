package com.training.tinhla.training.splashscreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.training.tinhla.training.R
import com.training.tinhla.training.base.model.constant.ACTIVITY_ID
import com.training.tinhla.training.base.model.constant.CONSTANT.Companion.EXTRA_ACTIVITY
import com.training.tinhla.training.basemodel.BaseActivity
import com.training.tinhla.training.mainscreen.MainActivity


class SplashActivity : BaseActivity(), SplashInterface.View{
    override fun getContext(): Context {
        return app
    }

    override fun getActivityID(): String {
        return ACTIVITY_ID.SPLASH
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)
    }

    override fun openNextActivity(activityID: String) {
        val intent = createIntent()
        intent.putExtra(EXTRA_ACTIVITY, activityID)
        startActivity(intent)
    }

    private fun createIntent() : Intent {
        return Intent(this, MainActivity::class.java)
    }

    fun onOpenMainScreen(view: View) {
        val intent = createIntent()
        startActivity(intent)
    }
}
