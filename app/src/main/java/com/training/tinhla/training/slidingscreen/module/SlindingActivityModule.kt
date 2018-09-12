package com.training.tinhla.training.slidingscreen.module

import android.app.Activity
import com.training.tinhla.training.basemodule.PerActivity
import com.training.tinhla.training.slidingscreen.SlidingActivity
import com.training.tinhla.training.slidingscreen.SlidingInterface
import dagger.Binds
import dagger.Module

@Module(includes = [SlindingPresentModule::class])
abstract class SlindingActivityModule{

    @Binds
    @PerActivity
    abstract fun activity(activity: SlidingActivity): Activity

    @Binds
    @PerActivity
    abstract fun view(activity: SlidingActivity) : SlidingInterface.view
}