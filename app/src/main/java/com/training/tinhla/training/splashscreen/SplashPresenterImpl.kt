package com.training.tinhla.training.splashscreen

import javax.inject.Inject

class SplashPresenterImpl @Inject constructor (var view: SplashInterface.View) : SplashInterface.Presenter{

    init {

    }

    override fun getApi() {
    }
}