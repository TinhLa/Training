package com.training.tinhla.training.basemodel

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

open class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var app: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

    }
}