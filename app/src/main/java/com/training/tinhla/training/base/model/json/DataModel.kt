package com.training.tinhla.training.base.model.json

class DataModel {
    var templateId : String ?= null
    var templateVersion:Long ?= null
    var templateBody : TemplateBodyModel ?= null
    var templateButton: TemplateButtonModel ?= null
    var minAppVersion: String ?= null
}