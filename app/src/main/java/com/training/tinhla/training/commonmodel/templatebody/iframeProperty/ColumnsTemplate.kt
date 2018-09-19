package com.training.tinhla.training.commonmodel.templatebody.iframeProperty

import com.google.gson.annotations.SerializedName

data class ColumnsTemplate(@SerializedName("contentType") val contentType: String, @SerializedName("parameter") val parameter: ParameterTemplate,
                           @SerializedName("percentWidth") val percentWidth: Int,
                           @SerializedName("height") val height: Int, @SerializedName("alignment") val alignment: String, @SerializedName("verticalAlignment") val verticalAlignment: String)