package com.training.tinhla.training.scrollscreen.module

import com.training.tinhla.training.basemodule.scope.PerActivity
import com.training.tinhla.training.scrollscreen.ScrollInterface
import com.training.tinhla.training.scrollscreen.ScrollPresenterImpl
import dagger.Binds
import dagger.Module

@Module
abstract class ScrollPresenterModule {
    @Binds
    @PerActivity
    abstract fun presenter(presenter: ScrollPresenterImpl): ScrollInterface.Presenter
}