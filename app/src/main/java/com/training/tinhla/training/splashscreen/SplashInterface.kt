package com.training.tinhla.training.splashscreen

import android.content.Context

class SplashInterface {
    interface Presenter{
        fun getApi()
    }

    interface View{
        fun getAppContext() : Context
    }
}

