package com.training.tinhla.training.nkhoi_srcollview

import android.content.Context
import com.training.tinhla.training.nkhoi_srcollview.model.ColumnIframeProperty
import com.training.tinhla.training.nkhoi_srcollview.model.ReadJson
import com.training.tinhla.training.nkhoi_srcollview.model.TemplateButton

class JsonPresenterImpl(var context: Context) : JsonInterface.presenterJson {

    private val readJson : ReadJson
    private val  textjson : String
    init {
        readJson = ReadJson(context)
        textjson = readJson.readFileJsonFromAsset(context)
    }
    override fun getTemplateID() : String {
       return readJson.getTemplateID(textjson)
    }

    override fun getTemplateVersion(): String {
        return readJson.getTemplateVersion(textjson)
    }
    override fun getTemplateButton(): ArrayList<TemplateButton> {
     return readJson.getTemplateButton(textjson)
    }

    override fun getTemplateBodyTye(): String {
       return readJson.getTemplateBodyType(textjson)
    }

    override fun getListImageIframeProperty(): ArrayList<String> {
      return readJson.getListImageIframeProperty(readJson.getIframeProperty(textjson))
    }

//    override fun getColumnsTemplateLines(): ArrayList<ColumnIframeProperty> {
////       return readJson.getColumnsTemplateLines(readJson.getIframeProperty(textjson))
//    }
}