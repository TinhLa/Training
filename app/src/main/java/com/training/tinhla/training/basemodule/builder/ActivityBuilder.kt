package com.training.tinhla.training.basemodule.builder

import android.app.Application
import com.training.tinhla.training.App
import com.training.tinhla.training.basemodule.scope.PerActivity
import com.training.tinhla.training.mainscreen.MainActivity
import com.training.tinhla.training.scrollscreen.ScrollActivity
import com.training.tinhla.training.scrollscreen.module.ScrollActivityModule
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

    @ContributesAndroidInjector(modules = [ScrollActivityModule::class])
    @PerActivity
    abstract fun bindScrollActivity(): ScrollActivity
}
