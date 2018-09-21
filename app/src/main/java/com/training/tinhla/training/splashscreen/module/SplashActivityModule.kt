package com.training.tinhla.training.splashscreen.module

import android.app.Activity
import android.support.v4.app.FragmentManager
import com.training.tinhla.training.basemodule.PerActivity
import com.training.tinhla.training.splashscreen.SplashActivity
import com.training.tinhla.training.splashscreen.SplashInterface
import dagger.Binds
import dagger.Module

@Module(includes = [SplashPresentModule::class, SplashHelperModule::class])
abstract class SplashActivityModule {

    @Binds
    @PerActivity
    abstract fun activity(activity: SplashActivity): Activity

    @Binds
    @PerActivity
    abstract fun view(activity: SplashActivity): SplashInterface.View
}