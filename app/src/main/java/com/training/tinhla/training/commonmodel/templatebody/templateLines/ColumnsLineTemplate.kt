package com.training.tinhla.training.commonmodel.templatebody.templateLines

import com.google.gson.annotations.SerializedName

class ColumnsLineTemplate(@SerializedName("contentType") val contentType: String, @SerializedName("parameter") val parameter: ParameterLineTemplate,
                          @SerializedName("percentWidth") val percentWidth: Int, @SerializedName("height") val height: Int, @SerializedName("alignment") val alignment: String,
                          @SerializedName("verticalAlignment") val verticalAlignment: String)