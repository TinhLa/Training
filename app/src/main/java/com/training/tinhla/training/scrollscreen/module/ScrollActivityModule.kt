package com.training.tinhla.training.scrollscreen.module

import android.app.Activity
import com.training.tinhla.training.basemodule.PerActivity
import com.training.tinhla.training.scrollscreen.ScrollActivity
import com.training.tinhla.training.scrollscreen.ScrollInterface
import dagger.Binds
import dagger.Module


@Module(includes = [ScrollPresenterModule::class, ScrollHelperModule::class])
abstract class ScrollActivityModule {

    @Binds
    @PerActivity
    abstract fun activity(activity: ScrollActivity): Activity

    @Binds
    @PerActivity
    abstract fun view(activity: ScrollActivity): ScrollInterface.View
}