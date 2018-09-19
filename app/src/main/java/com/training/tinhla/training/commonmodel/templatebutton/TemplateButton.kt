package com.training.tinhla.training.commonmodel.templatebutton

data class TemplateButton(val labelSet: String, val new : List<NewTemplate>, val complete:List<CompleteTemplate>, val expire:List<ExpireTemplate>,
                          val fail: List<FailTemplate>, val cancel:List<CancelTemplate>)
