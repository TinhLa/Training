package com.training.tinhla.training.splashscreen.module

import com.training.tinhla.training.basemodule.scope.PerActivity
import com.training.tinhla.training.splashscreen.SplashInterface
import com.training.tinhla.training.splashscreen.SplashPresenterImpl
import dagger.Binds
import dagger.Module

@Module
abstract class SplashPresentModule{

    @Binds
    @PerActivity
    abstract fun presenter(presenter: SplashPresenterImpl): SplashInterface.Presenter
}