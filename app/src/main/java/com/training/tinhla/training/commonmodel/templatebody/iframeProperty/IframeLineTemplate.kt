package com.training.tinhla.training.commonmodel.templatebody.iframeProperty

import com.google.gson.annotations.SerializedName

data class IframeLineTemplate(@SerializedName("lineType") val lineType: String,@SerializedName("columns") val columns: List<ColumnsTemplate>)