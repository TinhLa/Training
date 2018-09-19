package com.training.tinhla.training.commonmodel.templatebody

import com.google.gson.annotations.SerializedName
import com.training.tinhla.training.commonmodel.templatebody.iframeProperty.IframeProperty
import com.training.tinhla.training.commonmodel.templatebody.templateLines.TemplateLines

class TemplateBody(@SerializedName("templateType") val templateType:String, @SerializedName("iframeProperty") val iframeProperty:IframeProperty,@SerializedName("templateLines") val templateLines:List<TemplateLines>)