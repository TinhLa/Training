package com.training.tinhla.training.nkhoi_srcollview.model

import android.content.Context
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream

class ReadJson(context: Context) {
    private val textJson : String
    private var  listButton : ArrayList<TemplateButton>
    private var listColumnIframe : ArrayList<ColumnIframeProperty>
    init {
        listButton = ArrayList<TemplateButton>()
        listColumnIframe = ArrayList<ColumnIframeProperty>()
        textJson =  readFileJsonFromAsset(context)
    }
    fun getTemplateID(textJson : String) : String{
        val obj : JSONObject = JSONObject(textJson)
        return obj.getString("templateID")
    }

    fun getTemplateVersion(textJson : String) : String{
        val obj : JSONObject = JSONObject(textJson)
        return obj.getString("templateVersion")
    }

    fun getTemplateButton(textJson: String) : ArrayList<TemplateButton> {
        if(listButton.size>1) listButton.clear()
        val obj : JSONObject = JSONObject(textJson)
        val templateButton :String = obj.getString("templateBtn")
        val objButton : JSONObject = JSONObject(templateButton)
        listButton.add(TemplateButton(objButton.getString("labelSet")," "," "," "," "))
        listButton = getTemplaceEachButton("new",objButton.getString("new"),listButton)
        listButton = getTemplaceEachButton("complete",objButton.getString("complete"),listButton)
        listButton = getTemplaceEachButton("expire",objButton.getString("expire"),listButton)
        listButton = getTemplaceEachButton("fail",objButton.getString("fail"),listButton)
        listButton = getTemplaceEachButton("cancel",objButton.getString("cancel"),listButton)
        return listButton
    }

    fun getTemplaceEachButton(label : String,textJson : String , array : ArrayList<TemplateButton>) :ArrayList<TemplateButton>{
        val objArray : JSONArray = JSONArray(textJson)
        for (i in 0..(objArray.length()-1) step 1) {
            val obj: JSONObject = objArray.get(i) as JSONObject
            array.add(TemplateButton(label, obj.getString("textKey"),obj.getString("icon") ,obj.getString("state"), obj.getString("action")))
        }
        return array
    }

    fun getTemplateBodyType(textJson: String) :String{
        val obj : JSONObject = JSONObject(textJson)
        val objBody: JSONObject = JSONObject(obj.getString("templateBody"))
        return objBody.getString("templateType")
    }

    fun getIframeProperty(textJson: String): String{
        val obj : JSONObject = JSONObject(textJson)
        val objBody: JSONObject = JSONObject(obj.getString("templateBody"))
        return objBody.getString("iframeProperty")
    }

    fun getListImageIframeProperty(textJson: String):ArrayList<String>{
        var listImage : ArrayList<String> = ArrayList<String>()
        val obj : JSONObject = JSONObject(textJson)
        val objarray : JSONArray = JSONArray(obj.getString("images"))
        for(i in 0..objarray.length()-1 step 1){ listImage.add(objarray.getString(i)) }
        return listImage
    }
//
//    fun getColumnsTemplateLines(textJson: String) : ArrayList<ColumnIframeProperty>{
//        var listColumnIframeProperty : ArrayList<ColumnIframeProperty> = ArrayList<ColumnIframeProperty>()
//        val obj : JSONObject = JSONObject(textJson)
//        val objArray : JSONArray = JSONArray(obj.getString("templateLines"))
//        for(i in 0..objArray.length()-1 step 1){
//            val objTemplateLine: JSONObject = objArray.get(i) as JSONObject
//            Log.e("",""+objTemplateLine.getString("lineType"))
//            Log.e("",""+objTemplateLine.getString("columns"))
//         //   Log.e("",""+objTemplateLine.getInt("percentWidth"))
//       //     Log.e("",""+objTemplateLine.getInt("height"))
//            // Log.e("",""+objTemplateLine.getString("alignment"))
//            Log.e("",""+objTemplateLine.getString("verticalAlignment"))
//        }
//       return listColumnIframeProperty
//    }

    fun readFileJsonFromAsset(context: Context): String {
        var json: String? = null
        try {
            val ins : InputStream = context.assets.open("template7.json")
            val size = ins.available()
            val buffer = ByteArray(size)
            ins.read(buffer)
            ins.close()
            json = String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return json!!
    }





}