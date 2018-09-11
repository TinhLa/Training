package com.training.tinhla.training

import android.app.Activity
import android.app.Application
import com.training.tinhla.training.basemodule.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application() ,HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    override fun onCreate() {
        DaggerAppComponent.builder().application(this).build().inject(this)
        super.onCreate()
    }
}