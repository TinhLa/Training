package com.training.tinhla.training.basemodule

import android.content.Context
import com.training.tinhla.training.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideApplication(app: App): Context = app
}