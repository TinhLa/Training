package com.training.tinhla.training.slidingscreen.module

import com.training.tinhla.training.basemodule.PerActivity
import com.training.tinhla.training.slidingscreen.SlidingInterface
import com.training.tinhla.training.slidingscreen.SlidingPresenterImpl
import com.training.tinhla.training.slidingscreen.adapter.SlidingAdapter
import dagger.Binds
import dagger.Module

@Module
abstract class SlindingPresentModule{
    @Binds
    @PerActivity
    abstract fun presenter(presenter : SlidingPresenterImpl): SlidingInterface.presenter
}