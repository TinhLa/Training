package com.training.tinhla.training.splashscreen

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.training.tinhla.training.base.model.json.ButtonModel
import com.training.tinhla.training.base.model.json.TemplateBodyModel
import com.training.tinhla.training.base.model.json.TemplateButtonModel
import com.training.tinhla.training.base.model.json.TemplateLineModel
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import javax.inject.Inject

class JsonHelper @Inject constructor(var context:Context) {

    fun readJSONFromAsset(fileName:String) : String {
        var json = ""
        try {
            val inputStream = context.assets.open(fileName)
            json = inputStream.bufferedReader().use { bufferedReader: BufferedReader -> bufferedReader.readText() }
        } catch (e: IOException) {
            e.printStackTrace()
            Log.d("LOG", "read json from assets count error: " + e.toString())
        }

        return json
    }

    fun readBackgroundImages(templateBody: TemplateBodyModel?) : List<String> {
        val images = ArrayList<String>()

        if (templateBody != null && templateBody.iframeProperty != null) {

            val bgImages = templateBody.iframeProperty?.images

            if (bgImages != null) {
                if (bgImages.size > 0) {
                    val size = bgImages.size
                    for (i in 0..(size - 1)) {

                        images.add(bgImages.get(i))
                    }
                }
                else{
                    images.add("")
                }
            }
        }

        return images
    }

    fun readForegroundHeaderIFrame(templateBody: TemplateBodyModel?) : List<TemplateLineModel> {
        val lines = ArrayList<TemplateLineModel>()

        if (templateBody != null) {

            val iFrame = templateBody.iframeProperty

            if (iFrame != null) {
                val templateLines = iFrame.templateLines

                if (templateLines != null) {
                    val count = templateLines.size
                    for (i in 0..(count - 1)) {
                        val line: TemplateLineModel = templateLines.get(i)

                        lines.add(line)
                    }
                }
            }
        }

        return lines
    }

    fun readTemplateButtonsLines(templateButtons: JSONObject) : ArrayList<ButtonModel>? {
        var buttons:ArrayList<ButtonModel> ?= null

        // add keys in specific order
        val keys = ArrayList<String>()
        keys.add("new")
        keys.add("complete")
        keys.add("expire")
        keys.add("fail")
        keys.add("cancel")

        val gson = Gson()
        val count = keys.size
        for (i in 0..(count - 1)) {
            val key = keys.get(i)
            if (templateButtons.has(key)) {
                val line = templateButtons.get(key) as JSONArray
                buttons = readTemplateButtonsLine(gson, line)
                if (buttons != null && buttons.size > 0) {
                    break
                }
            }
        }

        return buttons
    }

    private fun readTemplateButtonsLine(gson: Gson, templateButtonsLineJSON: JSONArray): ArrayList<ButtonModel>? {
        val buttons = ArrayList<ButtonModel>()

        val count = templateButtonsLineJSON.length()

        for (i in 0..(count - 1)) {
            val buttonJSON = templateButtonsLineJSON.get(i) as JSONObject
            val templateButton:ButtonModel = gson.fromJson(buttonJSON.toString(), ButtonModel::class.java)
            Log.d("LOG", "template button " + templateButton.textKey + " : " +templateButton.state)

            if (templateButton.state.equals("disable")) {
                return null
            }else{
                buttons.add(templateButton)
            }
        }

        return buttons
    }
}