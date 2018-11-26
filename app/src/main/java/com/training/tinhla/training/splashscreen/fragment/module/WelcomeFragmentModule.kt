package com.training.tinhla.training.splashscreen.fragment.module

import android.support.v4.app.Fragment
import com.training.tinhla.training.basemodule.scope.PerFragment
import com.training.tinhla.training.splashscreen.fragment.WelcomeFragment
import com.training.tinhla.training.splashscreen.fragment.WelcomeInterface
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module (includes = [WelcomePresenterModule::class])
abstract class WelcomeFragmentModule {
    @Binds
    @PerFragment
    abstract fun bindFragment(fragment:WelcomeFragment) : Fragment

    @Binds
    @PerFragment
    abstract fun view(view: WelcomeFragment) : WelcomeInterface.View
}