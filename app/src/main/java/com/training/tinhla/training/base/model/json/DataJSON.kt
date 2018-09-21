package com.training.tinhla.training.base.model.json

class DataJSON {
    var templateId : String ?= null
    var templateVersion:Long ?= null
    var templateBody : TemplateBodyJSON ?= null
    var templateButton: TemplateButtonModel ?= null
    var minAppVersion: String ?= null
}