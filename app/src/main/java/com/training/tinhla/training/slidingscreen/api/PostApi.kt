package com.training.tinhla.training.slidingscreen.api

import com.training.tinhla.training.slidingscreen.model.Post
import io.reactivex.Observable
import retrofit2.http.GET

interface PostApi {
    @GET("/posts")
    fun getPost() : Observable<List<Post>>
}