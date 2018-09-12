package com.training.tinhla.training.basemodule

import android.content.Context
import com.training.tinhla.training.App
import com.training.tinhla.training.slidingscreen.api.PostApi
import com.training.tinhla.training.slidingscreen.module.SlidingNetworkModule
import com.training.tinhla.training.utils.BASE_URL
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [SlidingNetworkModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideApplication(app: App): Context = app

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit{
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }


}