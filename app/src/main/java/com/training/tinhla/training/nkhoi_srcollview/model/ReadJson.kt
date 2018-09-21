package com.training.tinhla.training.nkhoi_srcollview.model

import android.content.Context
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import com.google.gson.Gson



class ReadJson(context: Context) {
    private val textJson : String
    init {
        textJson =  readFileJsonFromAsset(context)
    }

    fun parseJsonInData() : Data{
        val gson = Gson()
        return gson.fromJson(textJson,Data::class.java)
    }

    fun readFileJsonFromAsset(context: Context): String {
        var json: String? = null
        try {
            val ins : InputStream = context.assets.open("template7.json")
            val size = ins.available()
            val buffer = ByteArray(size)
            ins.read(buffer)
            ins.close()
            json = String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return json!!
    }





}