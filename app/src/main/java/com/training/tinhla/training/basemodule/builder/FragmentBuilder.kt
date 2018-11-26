package com.training.tinhla.training.basemodule.builder

import com.training.tinhla.training.splashscreen.fragment.WelcomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {
    @ContributesAndroidInjector
    abstract fun contributeWelcomeFragment() : WelcomeFragment
}