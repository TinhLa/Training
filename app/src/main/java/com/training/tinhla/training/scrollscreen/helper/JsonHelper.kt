package com.training.tinhla.training.scrollscreen.helper

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.training.tinhla.training.base.model.json.ButtonModel
import com.training.tinhla.training.base.model.json.TemplateBodyModel
import com.training.tinhla.training.base.model.json.TemplateLineModel
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import javax.inject.Inject

/**
 * The class help reading data from JSON string in Asset
 */

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

    // Read background images and return list of image urls
    fun readBackgroundImages(templateBody: TemplateBodyModel) : List<String> {
        val images = ArrayList<String>()

        val iframeProperty = templateBody.iframeProperty

        if (iframeProperty != null) {
            val bgImages = iframeProperty.images

            if (bgImages != null) {
                val size = bgImages.size

                if (size > 0) {
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

    // Read data of header and return list of header lines
    fun readForegroundHeaderIFrame(templateBody: TemplateBodyModel) : List<TemplateLineModel> {
        val lines = ArrayList<TemplateLineModel>()

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

        return lines
    }

    // Read TemplateButton data and return list of button data
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

        // browse key in above order
        for (i in 0..(count - 1)) {
            val key = keys.get(i)

            // if this line has all enable buttons
            // return array of buttons and ignore other keys after
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

    // Read each node of template button JSON and return list if it contains all enable buttons or return null otherwise
    private fun readTemplateButtonsLine(gson: Gson, templateButtonsLineJSON: JSONArray): ArrayList<ButtonModel>? {
        val buttons = ArrayList<ButtonModel>()

        val count = templateButtonsLineJSON.length()

        for (i in 0..(count - 1)) {
            val buttonJSON = templateButtonsLineJSON.get(i) as JSONObject
            val templateButton:ButtonModel = gson.fromJson(buttonJSON.toString(), ButtonModel::class.java)

            if (templateButton.state.equals("disable")) {
                return null
            }else{
                buttons.add(templateButton)
            }
        }

        return buttons
    }
}