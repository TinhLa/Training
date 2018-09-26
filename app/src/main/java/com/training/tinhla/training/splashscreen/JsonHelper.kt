package com.training.tinhla.training.splashscreen

import android.content.Context
import android.util.Log
import com.training.tinhla.training.base.model.constant.CONTENT
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

    fun readTemplateButtonsLine(templateButtons: TemplateButtonModel?) {

    }
}