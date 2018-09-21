package com.training.tinhla.training.nkhoi_srcollview.model

class TemplateLine  {
    private var lineType : String
    private var columns : List<Column>

    constructor(lineType: String, columns: ArrayList<Column>) {
        this.lineType = lineType
        this.columns = columns
    }


    fun getLineType() : String {return lineType}
    fun getColumns():List<Column>{return columns}

    fun setLineType(value: String){lineType = value}
    fun setColumns(valua : ArrayList<Column>){columns = valua}

}