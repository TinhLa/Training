package com.training.tinhla.training.commonmodel.templatebody.templateLines

import com.google.gson.annotations.SerializedName

data class ParameterLineTemplate(@SerializedName("icon") val icon: String, @SerializedName("title") val title: String, @SerializedName("titleFontStyle") val titleFontStyle: String, @SerializedName("timeStamp") val timeStamp: String,
                                 @SerializedName("timeStampFontStyle") val timeStampFontStyle: String, @SerializedName("timeStampFontColor") val timeStampFontColor: String, @SerializedName("backgroundColor") val backgroundColor: String,
                                 @SerializedName("text") val text: String, @SerializedName("fontStyle") val fontStyle: String, @SerializedName("fontColor") val fontColor: String)