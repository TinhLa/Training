package com.training.tinhla.training.nkhoi_srcollview.model

class Parameters  {
    private var icon : String
    private var url :String
    private var text :String
    private var fontName :String
    private var fontStyle :String
    private var fontColor :String
    private var backgroundColor : String
    private var images : ArrayList<String>

    constructor(icon: String, url: String, text: String, fontName: String, fontStyle: String, fontColor: String, backgroundColor: String, images: ArrayList<String>) {
        this.icon = icon
        this.url = url
        this.text = text
        this.fontName = fontName
        this.fontStyle = fontStyle
        this.fontColor = fontColor
        this.backgroundColor = backgroundColor
        this.images = images
    }

    fun getParameterIcon():String{return icon}
    fun getParameterUrl() : String{ return url }
    fun getParameterText() : String{ return text }
    fun getParameterFontName() : String{ return fontName }
    fun getParameterFontStyle() : String{ return fontStyle }
    fun getParameterFontColor() : String{ return fontColor }
    fun getParameterBackground():String {return backgroundColor}
    fun getParameterImages():ArrayList<String>{return images}

    fun setParameterIcon(value: String){icon=value}
    fun setParameterUrl(value :String){url = value}
    fun setParameterText(value :String){text = value}
    fun setParameterFontName(value :String){fontName = value}
    fun setParameterFontStyle(value :String){fontStyle = value}
    fun setParameterFontColor(value :String){fontColor = value}
    fun setParameterBackground(value:String){backgroundColor=value}
    fun setParametterImages(value :ArrayList<String>){images=value}
}