package com.training.tinhla.training.commonmodel.templatebody.iframeProperty

import com.google.gson.annotations.SerializedName

data class IframeProperty(@SerializedName("images")val images: List<String>,@SerializedName("templateLines") val templateLines: List<IframeLineTemplate>)