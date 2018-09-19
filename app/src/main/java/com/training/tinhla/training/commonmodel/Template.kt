package com.training.tinhla.training.commonmodel

import com.google.gson.annotations.SerializedName
import com.training.tinhla.training.commonmodel.templatebody.TemplateBody
import com.training.tinhla.training.commonmodel.templatebutton.TemplateButton

data class Template(@SerializedName("templateId") val templateId: String, @SerializedName("templateVersion") val templateVersion: Int,
                    @SerializedName("templateBody") val templateBody: TemplateBody, @SerializedName("templateButton") val templateButton: TemplateButton,
                    @SerializedName("minAppVersion") val minAppVersion: String)