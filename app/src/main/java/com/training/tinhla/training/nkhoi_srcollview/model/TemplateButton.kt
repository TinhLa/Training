package com.training.tinhla.training.nkhoi_srcollview.model

class TemplateButton  {
    private var label :String
    private var textKey :String
    private var icon :String
    private var state :String
    private var action :String

    constructor(label: String, textKey: String, icon: String, state: String, action: String) {
        this.label = label
        this.textKey = textKey
        this.icon = icon
        this.state = state
        this.action = action
    }

    fun getLabel() : String{ return label }
    fun getTextKey() : String{ return textKey }
    fun getIcon() : String{ return icon }
    fun getState() : String{ return state }
    fun getAction() : String{ return action }

    fun setLabel(value :String){label = value}
    fun setTextkey(value :String){textKey = value}
    fun setIcon(value :String){icon = value}
    fun setState(value :String){state = value}
    fun setAction(value :String){action = value}



}