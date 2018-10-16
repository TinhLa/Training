package com.training.tinhla.training.basemodel

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.training.tinhla.training.base.model.constant.ACTIVITY_ID
import com.training.tinhla.training.base.model.constant.CONSTANT
import dagger.android.AndroidInjection
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var app: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        _readIntent()
    }

    private fun _readIntent() {
        if (intent != null && intent.extras != null) {
            val extras = intent.extras
            if (extras.containsKey(CONSTANT.EXTRA_ACTIVITY)) {
                val openedActivity = extras.getString(CONSTANT.EXTRA_ACTIVITY)

                if (openedActivity != getActivityID()) {
                    openNextActivity(openedActivity)
                }
            }
            readIntentIfAvailable(extras)
        }
    }

    abstract fun getActivityID() : String

    open protected fun readIntentIfAvailable(extras: Bundle?) {}

    open protected fun openNextActivity(activityID: String) {}
}