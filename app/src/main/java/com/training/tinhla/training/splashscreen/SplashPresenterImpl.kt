package com.training.tinhla.training.splashscreen

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.training.tinhla.training.App
import com.training.tinhla.training.base.model.constant.CONTENT
import com.training.tinhla.training.base.model.constant.LINE
import com.training.tinhla.training.base.model.json.*
import com.training.tinhla.training.splashscreen.header_images.BackgroundImageFragment
import org.json.JSONObject
import javax.inject.Inject

class SplashPresenterImpl @Inject constructor (var view: SplashInterface.View, var jsonHelper: JsonHelper) : SplashInterface.Presenter{

    @Inject
    lateinit var imagesSliderAdapter:FragmentsViewPager

    init {

    }

    override fun onCreate() {
        val jsonStr = jsonHelper.readJSONFromAsset("template10.json")
        val json = JSONObject(jsonStr)

        if (json.has("templateBody")) {
            processData(json)
        }else if (json.has("data")){
            val dataObject = json.getJSONObject("data")
            if (dataObject.has("templateBody")) {
                processData(dataObject)
            }
        }
    }

    private fun processData(json: JSONObject) {
        val data = Gson().fromJson<DataModel>(json.toString(), DataModel::class.java)

        if (data != null && data.templateBody != null) {

            // read images for background header IFrame
            processBackgroundImagesHeaderIFrame(data.templateBody)

            processForegroundHeaderIFrame(data.templateBody)

            val body = data.templateBody?.templateLines
            processBody(body)
        }

        if (json.has("templateButton")) {
            val templateButonJSON = json.get("templateButton") as JSONObject
            processTemplateButtonsLine(templateButonJSON)
        }
    }

    // Read and show background images of header IFrame
    private fun processBackgroundImagesHeaderIFrame(templateBody: TemplateBodyModel?) {
        val images = jsonHelper.readBackgroundImages(templateBody)
        val size = images.size

        for (i in 0..(size - 1)) {
            val image = images.get(i)
            imagesSliderAdapter.add(BackgroundImageFragment.newInstance(image))
        }
        view.setupBgHeaderViewPager(imagesSliderAdapter)
    }

    private fun processForegroundHeaderIFrame(templateBody: TemplateBodyModel?) {
        val lines = jsonHelper.readForegroundHeaderIFrame(templateBody)
        val count = lines.size
        for (i in 0..(count - 1)) {
            val line = lines.get(i)
            view.addHeaderLine(line)
        }
    }

    private fun processBody(body: ArrayList<TemplateLineModel>?) {
        if (body != null) {
            val count = body.size
            for (i in 0..(count-1)) {
                val line = body.get(i)
                val lineType = line.lineType
                when (lineType) {
                    LINE.NORMAL.value -> {
                        addBodyLine(line)
                    }

                    LINE.DRAW_LINE.value -> {
                        addBodyDrawLine()
                    }

                    LINE.EMPTY_LINE.value -> {
                        addBodyEmptyLine()
                    }

                    else -> {

                    }
                }
            }
        }
    }

    private fun addBodyDrawLine() {
        view.addDrawLineInBody()
    }

    private fun addBodyEmptyLine() {
        view.addEmptyLineInBody()
    }

    private fun addBodyLine(line: TemplateLineModel) {
        view.addBodyLine(line)
    }

    private fun processTemplateButtonsLine(templateButtonsJSON: JSONObject) {
        val templateButtons = jsonHelper.readTemplateButtonsLines(templateButtonsJSON)
        view.addTemplateButtons(templateButtons)
    }
}