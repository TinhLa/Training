package com.training.tinhla.training.splashscreen.module

import android.app.Activity
import android.support.v4.app.FragmentManager
import com.training.tinhla.training.splashscreen.SplashActivity
import dagger.Module
import dagger.Provides

@Module
class SplashHelperModule {
    @Provides
    fun provideFragmentManager(activity: SplashActivity): FragmentManager = activity.supportFragmentManager
}