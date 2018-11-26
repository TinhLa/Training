package com.training.tinhla.training.basemodule.module

import android.app.Activity
import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import com.training.tinhla.training.App
import com.training.tinhla.training.splashscreen.SplashActivity
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideApplication(app: App): Context = app
}