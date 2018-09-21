package com.training.tinhla.training.nkhoi_srcollview.model

class Column {
    private var contentType : String
    private var percentWidth : Int
    private var height : Int
    private var alignment : String
    private var verticalAlignment : String
    private var parameter : Parameters

    constructor(contentType: String, percentWidth: Int, height: Int, alignment: String, verticalAlignment: String, parameter: Parameters) {
        this.contentType = contentType
        this.percentWidth = percentWidth
        this.height = height
        this.alignment = alignment
        this.verticalAlignment = verticalAlignment
        this.parameter = parameter
    }

    fun getColumnContentType() : String {return contentType}
    fun getColumnPercentWidth() : Int {return  percentWidth}
    fun getColumnHeight() : Int {return height}
    fun getColumnAlignment() : String {return alignment}
    fun getColumnVerticalAlignment() : String {return verticalAlignment}
    fun getColumnParameter() : Parameters {return parameter}

    fun setColumnContentType(value: String){contentType = value}
    fun setColumnPercentWidth(value: Int){percentWidth = value}
    fun setColumnHeight(value: Int){height = value}
    fun setColumnAlignment(value: String){alignment = value}
    fun setColumnVerticalAlignment(value: String){verticalAlignment = value}
    fun setColumnParameter(value: Parameters){parameter=value}
}