package com.training.tinhla.training.splashscreen.fragment.module

import com.training.tinhla.training.basemodule.scope.PerFragment
import com.training.tinhla.training.splashscreen.fragment.WelcomeInterface
import com.training.tinhla.training.splashscreen.fragment.WelcomePresenterImpl
import dagger.Binds
import dagger.Module

@Module
abstract class WelcomePresenterModule {
    @Binds
    @PerFragment
    abstract fun bindPresenter(presenter:WelcomePresenterImpl) : WelcomeInterface.Presenter
}