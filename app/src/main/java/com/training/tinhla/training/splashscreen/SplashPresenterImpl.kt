package com.training.tinhla.training.splashscreen

import com.google.gson.Gson
import com.training.tinhla.training.base.model.constant.LINE
import com.training.tinhla.training.base.model.constant.NODE
import com.training.tinhla.training.base.model.json.*
import com.training.tinhla.training.splashscreen.header_images.BackgroundImageFragment
import com.training.tinhla.training.splashscreen.header_images.FragmentsViewPager
import com.training.tinhla.training.splashscreen.helper.JsonHelper
import org.json.JSONObject
import javax.inject.Inject

class SplashPresenterImpl @Inject constructor (var view: SplashInterface.View, var jsonHelper: JsonHelper) : SplashInterface.Presenter{

    val JSON_FILE = "template10.json"

    @Inject
    lateinit var imagesSliderAdapter: FragmentsViewPager

    init {

    }

    override fun onCreate() {
        val jsonStr = jsonHelper.readJSONFromAsset(JSON_FILE)
        val json = JSONObject(jsonStr)

        // setup to all JSON file probably
        if (json.has(NODE.TEMPLATE_BODY)) {
            processData(json)
        }else if (json.has(NODE.DATA)){
            val dataObject = json.getJSONObject(NODE.DATA)
            if (dataObject.has(NODE.TEMPLATE_BODY)) {
                processData(dataObject)
            }
        }
    }

    private fun processData(json: JSONObject) {
        val data = Gson().fromJson<DataModel>(json.toString(), DataModel::class.java)

        if (data != null && data.templateBody != null) {

            val templateBody = data.templateBody

            if (templateBody != null) {

                // read images for background header IFrame
                processBackgroundImagesHeaderIFrame(templateBody)

                processForegroundHeaderIFrame(templateBody)

                val body = templateBody.templateLines
                if(body != null)
                    processBody(body)
            }
        }

        if (json.has(NODE.TEMPLATE_BUTTON)) {
            val templateButonJSON = json.get(NODE.TEMPLATE_BUTTON) as JSONObject
            processTemplateButtonsLine(templateButonJSON)
        }
    }

    // Read and show background images of header IFrame
    private fun processBackgroundImagesHeaderIFrame(templateBody: TemplateBodyModel) {
        val images = jsonHelper.readBackgroundImages(templateBody)
        val size = images.size

        for (i in 0..(size - 1)) {
            val image = images.get(i)
            imagesSliderAdapter.add(BackgroundImageFragment.newInstance(image))
        }
        view.setupBgHeaderViewPager(imagesSliderAdapter)
    }

    // Read and show views of header IFrame
    private fun processForegroundHeaderIFrame(templateBody: TemplateBodyModel) {
        val lines = jsonHelper.readForegroundHeaderIFrame(templateBody)
        val count = lines.size
        for (i in 0..(count - 1)) {
            val line = lines.get(i)
            view.addHeaderLine(line)
        }
    }

    // Read and show views of body
    private fun processBody(body: ArrayList<TemplateLineModel>) {
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