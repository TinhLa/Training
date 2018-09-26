package com.training.tinhla.training.nkhoi_srcollview.presenterImpl

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.training.tinhla.training.R
import com.training.tinhla.training.nkhoi_srcollview.model.Data
import com.training.tinhla.training.nkhoi_srcollview.model.ReadJson
import com.training.tinhla.training.nkhoi_srcollview.presenterInterface.JsonInterfaceTemplateButton

class JsonPresenterTemplateBtnImpl (var context: Context) : JsonInterfaceTemplateButton.presenterTemplateBnt {



    private val dta : Data
    private val readJson : ReadJson
    private val  textjson : String
    init {
        readJson = ReadJson(context)
        textjson = readJson.readFileJsonFromAsset(context)
        dta = readJson.parseJsonInData()
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun getListButton(viewGroup: ViewGroup) {
        if(dta.templateBtn?.new?.size!!>1) checkEnableTwoButton(viewGroup, dta.templateBtn?.new?.get(0)?.state!!,dta.templateBtn?.new?.get(1)?.state!!) else setOneButton(viewGroup)
//        if(dta.templateBtn?.complete?.size!!>1) setTwoButton() else setOneButton()
//        if(dta.templateBtn?.expire?.size!!>1) setTwoButton() else setOneButton()
//        if(dta.templateBtn?.fail?.size!!>1) setTwoButton() else setOneButton()
//        if(dta.templateBtn?.cancel?.size!!>1) setTwoButton() else setOneButton()
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun checkEnableTwoButton(viewGroup: ViewGroup, state1:String ,state2:String ) {
      if (state1.equals("enable")==true && state2.equals("enable")==true) setTwoButton(viewGroup)
    }

    override fun checkEnableOneButton(viewGroup: ViewGroup, state: String) {

    }
    override fun setOneButton(viewGroup: ViewGroup) {
        val linearLayoutParent :LinearLayout = LinearLayout(context)
        val paramParent = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT,1f)
        linearLayoutParent.layoutParams = paramParent
        linearLayoutParent.orientation = LinearLayout.HORIZONTAL

        val linearLayoutParent1 : LinearLayout = LinearLayout(context)
        linearLayoutParent1.layoutParams = paramParent
        linearLayoutParent1.setBackgroundColor(Color.parseColor("#000000"))

        val linearLayoutParent2 : LinearLayout = LinearLayout(context)
        linearLayoutParent1.layoutParams = paramParent
        linearLayoutParent1.setBackgroundColor(Color.parseColor("#ffffff"))

        val linearLayoutParent3 : LinearLayout = LinearLayout(context)
        linearLayoutParent1.layoutParams = paramParent
        linearLayoutParent1.setBackgroundColor(Color.parseColor("#000000"))

        linearLayoutParent.addView(linearLayoutParent1)
        linearLayoutParent.addView(linearLayoutParent2)
        linearLayoutParent.addView(linearLayoutParent3)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun setTwoButton(viewGroup: ViewGroup) {
        val linearLayoutParent :LinearLayout = LinearLayout(context)
        val paramParent = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT,1.5f)
        paramParent.setMargins(30,0,30,20)
        linearLayoutParent.layoutParams = paramParent
        linearLayoutParent.orientation = LinearLayout.HORIZONTAL

        val linearLayoutParent1 : LinearLayout = LinearLayout(context)
        linearLayoutParent1.layoutParams = paramParent
        linearLayoutParent1.orientation = LinearLayout.HORIZONTAL
        linearLayoutParent1.setOnClickListener(View.OnClickListener {
            Log.e("1","1")
        })


        val linearLayoutParent2 : LinearLayout = LinearLayout(context)
        linearLayoutParent2.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT,2f)
        linearLayoutParent2.setOnClickListener(View.OnClickListener {
            Log.e("2","2")
        })

        val linearLayoutParent3 : LinearLayout = LinearLayout(context)
        linearLayoutParent3.layoutParams = paramParent
        linearLayoutParent3.orientation = LinearLayout.HORIZONTAL
        linearLayoutParent3.setOnClickListener(View.OnClickListener {
            Log.e("3","3")
        })

        val parambt = LinearLayout.LayoutParams(150,150)
        val paramtext = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
        paramtext.setMargins(0,20,0,0)
        val requestOptions = RequestOptions()

        val button1 : ImageView = ImageView(context)
        button1.layoutParams = parambt
        val url1 : String?  = dta.templateBtn?.new?.get(0)?.icon
        button1.scaleType = ImageView.ScaleType.FIT_CENTER
        requestOptions.placeholder(R.drawable.btn_cancel)
        Glide.with(context).setDefaultRequestOptions(requestOptions).load(url1).into(button1);
        button1.setOnClickListener(View.OnClickListener {
            Toast.makeText(context,"cancel",Toast.LENGTH_SHORT).show()
        })

        val textView1 : TextView = TextView(context)
        textView1.layoutParams = paramtext
        textView1.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
        textView1.setText(dta.templateBtn?.new?.get(0)?.textKey)
        textView1.setOnClickListener(View.OnClickListener {
            Toast.makeText(context,"cancel",Toast.LENGTH_SHORT).show()
        })

        val textView2 : TextView = TextView(context)
        textView2.layoutParams = paramtext
        textView2.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
        textView2.setText(dta.templateBtn?.new?.get(1)?.textKey)
        textView2.setOnClickListener(View.OnClickListener {
            Toast.makeText(context,"comfig",Toast.LENGTH_SHORT).show()
        })

        val button2 : ImageView = ImageView(context)
        button2.layoutParams = parambt
        val url2 : String?  = dta.templateBtn?.new?.get(1)?.icon
        button2.scaleType = ImageView.ScaleType.FIT_CENTER
        requestOptions.placeholder(R.drawable.btn_confirm)
        Glide.with(context).setDefaultRequestOptions(requestOptions).load(url2).into(button2);
        button2.setOnClickListener(View.OnClickListener {
            Toast.makeText(context,"comfig",Toast.LENGTH_SHORT).show()
        })


        linearLayoutParent1.addView(button1)
        linearLayoutParent1.addView(textView1)
        linearLayoutParent3.addView(textView2)
        linearLayoutParent3.addView(button2)

        linearLayoutParent.addView(linearLayoutParent1)
        linearLayoutParent.addView(linearLayoutParent2)
        linearLayoutParent.addView(linearLayoutParent3)
        viewGroup.addView(linearLayoutParent)
    }
}