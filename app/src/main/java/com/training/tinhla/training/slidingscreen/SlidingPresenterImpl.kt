package com.training.tinhla.training.slidingscreen

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.training.tinhla.training.commonmodel.Template
import com.training.tinhla.training.slidingscreen.api.PostApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject

class SlidingPresenterImpl @Inject constructor(var view: SlidingInterface.view, val context: Context) : SlidingInterface.presenter {
    override fun getJsonTemplate() {

        //using rxAndroid to instead the asyncTask
        Observable.fromCallable<Template> {
            val myString = getFile()
            return@fromCallable Gson().fromJson(myString, Template::class.java)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ rtl -> view.onCreateFrameHeader(rtl) }, { err -> view.error(err.toString())})

    }

    private fun getFile(): String {
        var json: String? = null
        try {
            val inputStream: InputStream = context.assets.open("template9.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return json!!

    }

    @Inject
    lateinit var postApi: PostApi

    private var subcription: Disposable? = null
    override fun getListPost() {
        subcription = postApi.getPost()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { post -> view.success(post) },
                        { err -> view.error(err.toString()) }
                )
    }
}