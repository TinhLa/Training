package com.training.tinhla.training.splashscreen

import android.content.Context
import javax.inject.Inject

class ReadJson {
    @Inject
    private lateinit var app:Context

    fun readJSONFromAsset(){
        var json:String ?= null
       try{
           var inputStream = app.assets.open("template1.json")
           
       }
    }
}