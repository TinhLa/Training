package com.training.tinhla.training.nkhoi_srcollview.model

class TemplateBody {
    private var templateType : String
    private var iframeProperty: IframeProperty
    private var templateLines : List<TemplateLine>

    constructor(templateType: String, iframeProperty: IframeProperty, templateLines: List<TemplateLine>) {
        this.templateType = templateType
        this.iframeProperty = iframeProperty
        this.templateLines = templateLines
    }


    fun getTemplateType():String{return templateType}
    fun getIframeProperty():IframeProperty{return iframeProperty}
    fun getTemplateLínes():List<TemplateLine>{return templateLines}

    fun setTemplateType(value:String){templateType=value}
    fun setIframeProperty(value:IframeProperty){iframeProperty=value}
    fun setTemplateLines(value:List<TemplateLine>){templateLines=value}
}