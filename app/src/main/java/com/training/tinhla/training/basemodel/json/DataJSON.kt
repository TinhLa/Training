package com.training.tinhla.training.basemodel.json

class DataJSON {
    var templateId : String ?= null
    var templateVersion:Long ?= null
    var templateBody : TemplateBodyJSON ?= null
    var templateButton: TemplateButton ?= null
    var minAppVersion: String ?= null
}