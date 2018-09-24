package com.training.tinhla.training.nkhoi_srcollview.model

class TemplateButton  {
     var labelSet :String?=null
     var minAppVersion :String?=null
     var new :ArrayList<ButtonOfTemplate>?=null
     var complete: ArrayList<ButtonOfTemplate>?=null
     var expire: ArrayList<ButtonOfTemplate>?=null
     var fail: ArrayList<ButtonOfTemplate>?=null
     var cancel : ArrayList<ButtonOfTemplate>?=null

//    constructor(labelSet: String, minAppVersion: String, new: ArrayList<ButtonOfTemplate>, complete: ArrayList<ButtonOfTemplate>, expire: ArrayList<ButtonOfTemplate>, fail: ArrayList<ButtonOfTemplate>, cancel: ArrayList<ButtonOfTemplate>) {
//        this.labelSet = labelSet
//        this.minAppVersion = minAppVersion
//        this.new = new
//        this.complete = complete
//        this.expire = expire
//        this.fail = fail
//        this.cancel = cancel
//    }
//
//    fun getLabelSet():String{return labelSet}
//    fun getMinAppVersion():String{return minAppVersion}
//    fun getNewButton():ArrayList<ButtonOfTemplate>{return new}
//    fun getCompleteButton():ArrayList<ButtonOfTemplate>{return complete}
//    fun getExpireButton():ArrayList<ButtonOfTemplate>{return expire}
//    fun getFailButton():ArrayList<ButtonOfTemplate>{return fail}
//    fun getCancelButton():ArrayList<ButtonOfTemplate>{return cancel}
//
//    fun setLabelSet(value:String){labelSet =value}
//    fun setMinAppVersion(value:String){minAppVersion=value}
//    fun setNewButton(value:ArrayList<ButtonOfTemplate>){new = value}
//    fun setCompleteButton(value:ArrayList<ButtonOfTemplate>){complete = value}
//    fun setExpireButton(value:ArrayList<ButtonOfTemplate>){expire = value}
//    fun setFailButton(value:ArrayList<ButtonOfTemplate>){fail = value}
//    fun setCancelButton(value:ArrayList<ButtonOfTemplate>){cancel = value}

}


class ButtonOfTemplate{
     var textKey : String?=null
     var icon:String?=null
     var state :String?=null
     var action :String?=null

//    constructor(textkey: String, icon: String, state: String, action: String) {
//        this.textKey = textkey
//        this.icon = icon
//        this.state = state
//        this.action = action
//    }
//    fun getTextKey(): String {return textKey}
//    fun geticon(): String {return icon}
//    fun getState(): String {return state}
//    fun getAction(): String {return action}
//
//    fun setTextKey(value :String){textKey = value}
//    fun setIcon(value :String){icon = value}
//    fun setState(value :String){state = value}
//    fun setAction(value :String){action = value}
}