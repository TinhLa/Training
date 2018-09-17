package com.training.tinhla.training.splashscreen

import android.content.Context
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import javax.inject.Inject

class JsonHelper @Inject constructor(var context:Context) {

    fun readJSONFromAsset(fileName:String) : String? {
        var json:String ?= null
        try {
            var inputStream = context.assets.open(fileName)
            json = inputStream.bufferedReader().use { bufferedReader: BufferedReader -> bufferedReader.readText() }
        } catch (e: IOException) {
            e.printStackTrace()
            Log.d("LOG", "read json from assets count error: " + e.toString())
        }
        return json
    }



    fun readBackgroundImages(jsonArray: JSONArray) {

    }

    fun readTemplateLines(jsonArray: JSONArray) {

    }

    fun readTemplateLine(jsonObject: JSONObject) {

    }

}