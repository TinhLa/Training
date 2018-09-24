package com.training.tinhla.training.splashscreen

import com.google.gson.Gson
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
        val gson = Gson()

        val data = gson.fromJson<DataModel>(json.toString(), DataModel::class.java)

        if (data != null && data.templateBody != null) {

            // read images for background header IFrame
            processBackgroundImagesHeaderIFrame(data.templateBody)

            processForegroundHeaderIFrame(data.templateBody)

            val body = data.templateBody?.templateLines
            processBody(body)
        }

        processTemplateButtonsLine(data.templateButton)
    }

    private fun processBackgroundImagesHeaderIFrame(templateBody: TemplateBodyModel?) {
        if (templateBody != null && templateBody.iframeProperty != null) {
            val bgImages = templateBody.iframeProperty?.images

            if (bgImages != null && bgImages.size > 0) {
                val size = bgImages.size
                for (i in 0..(size - 1)) {
                    imagesSliderAdapter.add(BackgroundImageFragment.newInstance(bgImages.get(i)))
                }

                view.setupBgHeaderViewPager(imagesSliderAdapter)
            }
        }
    }

    private fun processForegroundHeaderIFrame(templateBody: TemplateBodyModel?) {
        var iFrame = templateBody?.iframeProperty

        if (iFrame != null) {
            var templateLines = iFrame.templateLines

            if (templateLines != null) {
                var count = templateLines.size
                for (i in 0..(count - 1)) {
                    var item:TemplateLineModel = templateLines.get(i)
                    var columns = item.columns
                    var _count = columns?.size?:0

                    // normal type
                    if (_count == 1) {
                        when (item.columns?.get(0)?.contentType) {
                            CONTENT.TEXT.value -> readTextHeader(item.columns?.get(0))

                            CONTENT.IMAGE.value -> readImageHeader(item.columns?.get(0))
                        }
                    }
                }
            }
        }
    }

    private fun processTemplateButtonsLine(templateButtons: TemplateButtonModel?) {
        if (templateButtons != null && templateButtons.new != null) {
            view.createNewButtons(templateButtons.new!!)
        }
    }

    private fun processBody(body: ArrayList<TemplateLineModel>?) {
        if (body != null) {
            val count = body.size?:0
            for (i in 0..(count-1)) {
                val line = body.get(i)
                val lineType = line.lineType
                when (lineType) {
                    LINE.NORMAL.value -> {
                        addBodyLine(line)
                    }

                    LINE.DRAW_LINE.value -> {
                        view.addDrawLineInBody()
                    }

                    LINE.EMPTY_LINE.value -> {
                        view.addEmptyLineInBody()
                    }

                    else -> {

                    }
                }
            }
        }
    }

    private fun addBodyLine(line: TemplateLineModel) {
        view.addBodyLine(line)
    }

    private fun readImageHeader(columnModel: ColumnModel?) {
        if (columnModel == null) {
            return
        }
        view.addImageViewToHeaderIFrame(columnModel)
    }

    private fun readTextHeader(columnModel: ColumnModel?) {
        if (columnModel == null) {
            return
        }
        view.addTextViewToHeaderIFrame(columnModel)
    }

}