package com.training.tinhla.training.nkhoi_srcollview.model

class TemplateButton  {
    private var labelSet :String
    private var minAppVersion :String
    private var new :ArrayList<ButtonOfTemplate>
    private var complete: ArrayList<ButtonOfTemplate>
    private var expire: ArrayList<ButtonOfTemplate>
    private var fail: ArrayList<ButtonOfTemplate>
    private var cancel : ArrayList<ButtonOfTemplate>

    constructor(labelSet: String, minAppVersion: String, new: ArrayList<ButtonOfTemplate>, complete: ArrayList<ButtonOfTemplate>, expire: ArrayList<ButtonOfTemplate>, fail: ArrayList<ButtonOfTemplate>, cancel: ArrayList<ButtonOfTemplate>) {
        this.labelSet = labelSet
        this.minAppVersion = minAppVersion
        this.new = new
        this.complete = complete
        this.expire = expire
        this.fail = fail
        this.cancel = cancel
    }

    fun getLabelSet():String{return labelSet}
    fun getMinAppVersion():String{return minAppVersion}
    fun getNewButton():ArrayList<ButtonOfTemplate>{return new}
    fun getCompleteButton():ArrayList<ButtonOfTemplate>{return complete}
    fun getExpireButton():ArrayList<ButtonOfTemplate>{return expire}
    fun getFailButton():ArrayList<ButtonOfTemplate>{return fail}
    fun getCancelButton():ArrayList<ButtonOfTemplate>{return cancel}

    fun setLabelSet(value:String){labelSet =value}
    fun setMinAppVersion(value:String){minAppVersion=value}
    fun setNewButton(value:ArrayList<ButtonOfTemplate>){new = value}
    fun setCompleteButton(value:ArrayList<ButtonOfTemplate>){complete = value}
    fun setExpireButton(value:ArrayList<ButtonOfTemplate>){expire = value}
    fun setFailButton(value:ArrayList<ButtonOfTemplate>){fail = value}
    fun setCancelButton(value:ArrayList<ButtonOfTemplate>){cancel = value}

}


class ButtonOfTemplate{
    private var textKey : String
    private var icon:String
    private var state :String
    private var action :String

    constructor(textkey: String, icon: String, state: String, action: String) {
        this.textKey = textkey
        this.icon = icon
        this.state = state
        this.action = action
    }
    fun getTextKey(): String {return textKey}
    fun geticon(): String {return icon}
    fun getState(): String {return state}
    fun getAction(): String {return action}

    fun setTextKey(value :String){textKey = value}
    fun setIcon(value :String){icon = value}
    fun setState(value :String){state = value}
    fun setAction(value :String){action = value}
}