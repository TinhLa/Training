package com.training.tinhla.training.commonmodel.templatebody.templateLines

import com.google.gson.annotations.SerializedName

data class TemplateLines(@SerializedName("lineType") val lineType:String,@SerializedName("columns") val columns:List<ColumnsLineTemplate>)