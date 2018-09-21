package com.training.tinhla.training.nkhoi_srcollview.model

class IframeProperty {
    private var images : List<String>
    private var templateLines : List<TemplateLine>

    constructor(images: ArrayList<String>, templateLines: ArrayList<TemplateLine>) {
        this.images = images
        this.templateLines = templateLines
    }

    fun getListImage():List<String>{return images}
    fun getTemplateLines():List<TemplateLine>{return templateLines}

    fun setListImage(value :ArrayList<String>){images=value}
    fun setTemplateLines(value :ArrayList<TemplateLine>){templateLines=value}
}