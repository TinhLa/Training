package com.training.tinhla.training.basemodel

import android.app.Fragment
import android.os.Bundle
import dagger.android.AndroidInjection

class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
    }
}