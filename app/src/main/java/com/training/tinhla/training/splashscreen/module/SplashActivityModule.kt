package com.training.tinhla.training.splashscreen.module

import android.support.v7.app.AppCompatActivity
import com.training.tinhla.training.basemodule.scope.PerActivity
import com.training.tinhla.training.splashscreen.SplashActivity
import com.training.tinhla.training.splashscreen.SplashInterface
import com.training.tinhla.training.splashscreen.fragment.module.WelcomeFragmentModule
import dagger.Binds
import dagger.Module

@Module(includes = [SplashPresentModule::class, SplashHelperModule::class])
abstract class SplashActivityModule {

    @Binds
    @PerActivity
    abstract fun bindActivity(activity: SplashActivity) : AppCompatActivity

    @Binds
    @PerActivity
    abstract fun view(activity: SplashActivity): SplashInterface.View
}