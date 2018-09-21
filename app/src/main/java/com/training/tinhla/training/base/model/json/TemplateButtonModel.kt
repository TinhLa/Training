package com.training.tinhla.training.base.model.json

class TemplateButtonModel {
    var labelSet:String ?= null
    var new:ArrayList<ButtonJSON> ?= null
    var complete:ArrayList<ButtonJSON> ?= null
    var expire:ArrayList<ButtonJSON> ?= null
    var fail : ArrayList<ButtonJSON> ?= null
    var cancel: ArrayList<ButtonJSON> ?= null
}