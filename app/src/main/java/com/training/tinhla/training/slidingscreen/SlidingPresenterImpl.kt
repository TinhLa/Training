package com.training.tinhla.training.slidingscreen

import com.training.tinhla.training.slidingscreen.api.PostApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SlidingPresenterImpl @Inject constructor(var view : SlidingInterface.view) : SlidingInterface.presenter {
    @Inject
    lateinit var postApi: PostApi

    private var subcription : Disposable? = null
    override fun getListPost() {
        subcription = postApi.getPost()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {post -> view.success(post)},
                        {err->view.error(err.toString())}
                )
    }
}