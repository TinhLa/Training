package com.training.tinhla.training.basemodule

import com.training.tinhla.training.App
import com.training.tinhla.training.basemodule.builder.ActivityBuilder
import com.training.tinhla.training.basemodule.builder.FragmentBuilder
import com.training.tinhla.training.basemodule.module.AppModule
import com.training.tinhla.training.basemodule.module.BaseModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivityBuilder::class, FragmentBuilder::class, BaseModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}