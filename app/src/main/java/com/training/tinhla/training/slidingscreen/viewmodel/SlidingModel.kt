package com.training.tinhla.training.slidingscreen.viewmodel

import android.content.Context
import android.databinding.BaseObservable
import com.training.tinhla.training.slidingscreen.model.Post

class SlidingModel(context: Context,mpost: Post) : BaseObservable() {
    private var posts = mpost
    fun setPost(post: Post) {
        posts = post
    }

    fun getUserId(): String {
        return posts.userId.toString()
    }

    fun getId(): String {
        return posts.id.toString()
    }

    fun getTitle(): String {
        return posts.title
    }
}