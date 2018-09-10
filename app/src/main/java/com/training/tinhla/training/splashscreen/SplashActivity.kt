package com.training.tinhla.training.splashscreen

import android.content.Context
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.widget.Toolbar
import android.view.View
import com.training.tinhla.training.R
import com.training.tinhla.training.basemodel.BaseActivity
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashInterface.View {
    @Inject
    lateinit var presenter: SplashInterface.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun getAppContext(): Context {
        return app
    }
}
