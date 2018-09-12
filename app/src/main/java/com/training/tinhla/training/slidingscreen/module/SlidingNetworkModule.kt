package com.training.tinhla.training.slidingscreen.module

import com.training.tinhla.training.slidingscreen.adapter.SlidingAdapter
import com.training.tinhla.training.slidingscreen.api.PostApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class SlidingNetworkModule{
    @Provides
    @Singleton
    fun providePost(retrofit: Retrofit): PostApi {
        return retrofit.create(PostApi::class.java)
    }

    @Provides
    @Singleton
    fun adapter() : SlidingAdapter{
        return SlidingAdapter()
    }
}
