package com.training.tinhla.training.basemodule

import android.app.Application
import com.google.gson.Gson
import com.training.tinhla.training.App
import com.training.tinhla.training.mainscreen.MainActivity
import com.training.tinhla.training.splashscreen.SplashActivity
import com.training.tinhla.training.splashscreen.module.SplashActivityModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
abstract class ActivityBuilder {

    @Binds
    @Singleton
    abstract fun application(app: App): Application

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [SplashActivityModule::class])
    @PerActivity
    abstract fun bindSplashActivity(): SplashActivity
}
