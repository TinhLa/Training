package com.training.tinhla.training.splashscreen

import android.util.Log
import com.google.gson.Gson
import com.training.tinhla.training.basemodel.json.TemplateBodyJSON
import org.json.JSONObject
import javax.inject.Inject

class SplashPresenterImpl @Inject constructor (var view: SplashInterface.View, var jsonHelper: JsonHelper) : SplashInterface.Presenter{

    init {
        var jsonStr = jsonHelper.readJSONFromAsset("template10.json")
        var json = JSONObject(jsonStr)

        var gson = Gson()
        var templateBodyJSON = json.getJSONObject("templateBody")
        var templateBody = gson.fromJson<TemplateBodyJSON>(templateBodyJSON.toString(), TemplateBodyJSON::class.java)

    }

    override fun getApi() {
    }
}