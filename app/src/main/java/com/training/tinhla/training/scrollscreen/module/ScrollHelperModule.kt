package com.training.tinhla.training.scrollscreen.module

import android.support.v4.app.FragmentManager
import com.training.tinhla.training.scrollscreen.ScrollActivity
import com.training.tinhla.training.splashscreen.SplashActivity
import dagger.Module
import dagger.Provides

@Module
class ScrollHelperModule {
    @Provides
    fun provideFragmentManager(activity: ScrollActivity): FragmentManager = activity.supportFragmentManager
}