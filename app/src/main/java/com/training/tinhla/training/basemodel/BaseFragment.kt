package com.training.tinhla.training.basemodel

import android.content.Context
import android.support.v4.app.Fragment
import com.training.tinhla.training.App
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

open class BaseFragment : Fragment() {
    @Inject
    lateinit var app:App

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}