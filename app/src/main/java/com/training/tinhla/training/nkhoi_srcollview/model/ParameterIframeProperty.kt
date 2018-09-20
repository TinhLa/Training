package com.training.tinhla.training.nkhoi_srcollview.model

class ParameterIframeProperty  {
    private var url :String
    private var text :String
    private var fontName :String
    private var fontStyle :String
    private var fontColor :String

    constructor(url: String, text: String, fontName: String, fontStyle: String, fontColor: String) {
        this.url = url
        this.text = text
        this.fontName = fontName
        this.fontStyle = fontStyle
        this.fontColor = fontColor
    }
    fun getParameterUrl() : String{ return url }
    fun getParameterText() : String{ return text }
    fun getParameterFontName() : String{ return fontName }
    fun getParameterFontStyle() : String{ return fontStyle }
    fun getParameterFontColor() : String{ return fontColor }

    fun setParameterUrl(value :String){url = value}
    fun setParameterText(value :String){text = value}
    fun setParameterFontName(value :String){fontName = value}
    fun setParameterFontStyle(value :String){fontStyle = value}
    fun setParameterFontColor(value :String){fontColor = value}
}