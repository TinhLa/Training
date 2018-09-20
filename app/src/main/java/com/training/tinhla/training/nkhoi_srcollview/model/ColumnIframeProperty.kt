package com.training.tinhla.training.nkhoi_srcollview.model

class ColumnIframeProperty {
    private var lineType : String
    private var contentType : String
    private var percentWidth : Int
    private var height : Int
    private var alignment : String
    private var verticalAlignment : String
    private var parameter : ParameterIframeProperty

    constructor(lineType: String, contentType: String, percentWidth: Int, height: Int, alignment: String, verticalAlignment: String, parameter: ParameterIframeProperty) {
        this.lineType = lineType
        this.contentType = contentType
        this.percentWidth = percentWidth
        this.height = height
        this.alignment = alignment
        this.verticalAlignment = verticalAlignment
        this.parameter = parameter
    }

    fun getColumnIframeLineType() : String {return lineType}
    fun getColumnIframeContentType() : String {return contentType}
    fun getColumnIframePercentWidth() : Int {return  percentWidth}
    fun getColumnIframeHeight() : Int {return height}
    fun getColumnIframeAlignment() : String {return alignment}
    fun getColumnIframeVerticalAlignment() : String {return verticalAlignment}
    fun getColumnIframeParameter() : ParameterIframeProperty {return parameter}

    fun setColumnIframeLineType(value: String){lineType = value}
    fun setColumnIframeContentType(value: String){contentType = value}
    fun setColumnIframePercentWidth(value: Int){percentWidth = value}
    fun setColumnIframeHeight(value: Int){height = value}
    fun setColumnIframeAlignment(value: String){alignment = value}
    fun setColumnIframeverticalAlignment(value: String){verticalAlignment = value}
    fun setColumnIframeParameter(value: ParameterIframeProperty){parameter=value}
}