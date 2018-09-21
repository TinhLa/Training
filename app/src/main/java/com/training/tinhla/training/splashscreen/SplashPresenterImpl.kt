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
        var jsonStr = jsonHelper.readJSONFromAsset("template10.json")
        var json = JSONObject(jsonStr)

        var gson = Gson()
        var templateBodyJSON = json.getJSONObject("templateBody")

        var templateBody = gson.fromJson<TemplateBodyJSON>(templateBodyJSON.toString(), TemplateBodyJSON::class.java)

        // read images for background header IFrame
        var bgImages = templateBody.iframeProperty?.images
        if (bgImages != null && bgImages.size > 0) {
            var size = bgImages.size
            for (i in 0..(size - 1)) {
                imagesSliderAdapter.add(BackgroundImageFragment.newInstance(bgImages.get(i)))
            }
            view.setupBgHeaderViewPager(imagesSliderAdapter)
        }

        var count = templateBody.iframeProperty?.templateLines?.size?:0
        for (i in 0..(count - 1)) {
            var item:TemplateLineJSON = templateBody?.iframeProperty?.templateLines!!.get(i)
            var columns = item.columns
            var _count = columns?.size?:0

            if (_count == 1) {
                when (item.columns?.get(0)?.contentType) {
                    CONTENT.TEXT.value -> readTextHeader(item.columns?.get(0))

                    CONTENT.IMAGE.value -> readImageHeader(item.columns?.get(0))
                }
            }
        }

        var body = templateBody.templateLines
        readBody(body)

        var templateButtonsJSON = json.getJSONObject("templateButton")
        var templateButtons = gson.fromJson<TemplateButtonModel>(templateButtonsJSON.toString(), TemplateButtonModel::class.java)
        readTemplateButtons(templateButtons)
    }

    private fun readTemplateButtons(templateButtons: TemplateButtonModel?) {
        if (templateButtons != null && templateButtons.new != null) {
            view.createNewButtons(templateButtons.new!!)
        }
    }

    private fun readBody(body: ArrayList<TemplateLineJSON>?) {
        var count = body?.size?:0
        for (i in 0..(count-1)) {
            var line = body?.get(i)
            if (line != null) {
                var lineType = line.lineType
                when (lineType) {
                    LINE.NORMAL.value -> {
                        var columnsCount = line.columns?.size?:0

                        if(columnsCount == 1)
                            readBodyLineNormal(line)
                        else if(columnsCount == 2)
                            addTwoColumnsInBodyLine(line)
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

    private fun addTwoColumnsInBodyLine(line: TemplateLineJSON) {
        view.addLineTwoColumnsInBodyLine(line)
    }

    private fun readBodyLineNormal(normalLineJSON: TemplateLineJSON?) {
        if (normalLineJSON == null || normalLineJSON.columns == null) {
            return
        }
        view.addNormalLineToBody(normalLineJSON.columns?.get(0)!!)
    }

    private fun readImageHeader(columnJSON: ColumnJSON?) {
        if (columnJSON == null) {
            return
        }
        view.addImageViewToHeaderIFrame(columnJSON)
    }

    private fun readTextHeader(columnJSON: ColumnJSON?) {
        if (columnJSON == null) {
            return
        }
        view.addTextViewToHeaderIFrame(columnJSON)
    }

}