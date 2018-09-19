package com.training.tinhla.training.commonmodel.templatebody.iframeProperty

import com.google.gson.annotations.SerializedName

data class ParameterTemplate(@SerializedName("text") val text: String, @SerializedName("url") val url: String,
                             @SerializedName("fontStyle") val fontStyle: String, @SerializedName("fontColor") val fontColor: String)