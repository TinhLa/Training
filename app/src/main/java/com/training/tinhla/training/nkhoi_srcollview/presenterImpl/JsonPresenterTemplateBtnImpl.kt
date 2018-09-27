package com.training.tinhla.training.nkhoi_srcollview.presenterImpl

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Log
import android.view.Gravity
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
    private var flag : Int =0
    init {
        readJson = ReadJson(context)
        textjson = readJson.readFileJsonFromAsset(context)
        dta = readJson.parseJsonInData()
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun getListButton(viewGroup: ViewGroup) {
        if(dta.templateBtn?.new?.size!!>1)
            checkEnableTwoButton(viewGroup,
            dta.templateBtn?.new?.get(0)?.state!!,dta.templateBtn?.new?.get(0)?.icon!!,dta.templateBtn?.new?.get(0)?.textKey!!,
            dta.templateBtn?.new?.get(1)?.state!!,dta.templateBtn?.new?.get(1)?.icon!!,dta.templateBtn?.new?.get(1)?.textKey!!)
        else checkEnableOneButton(viewGroup,
            dta.templateBtn?.new?.get(0)?.state!!,dta.templateBtn?.new?.get(0)?.icon!!,dta.templateBtn?.new?.get(0)?.textKey!!)


        if(dta.templateBtn?.complete?.size!!>1)
            checkEnableTwoButton(viewGroup,
            dta.templateBtn?.complete?.get(0)?.state!!,dta.templateBtn?.complete?.get(0)?.icon!!,dta.templateBtn?.complete?.get(0)?.textKey!!,
            dta.templateBtn?.complete?.get(1)?.state!!,dta.templateBtn?.complete?.get(1)?.icon!!,dta.templateBtn?.complete?.get(1)?.textKey!!)
        else checkEnableOneButton(viewGroup,
            dta.templateBtn?.complete?.get(0)?.state!!,dta.templateBtn?.complete?.get(0)?.icon!!,dta.templateBtn?.complete?.get(0)?.textKey!!)


        if(dta.templateBtn?.expire?.size!!>1)
            checkEnableTwoButton(viewGroup,
            dta.templateBtn?.expire?.get(0)?.state!!,dta.templateBtn?.expire?.get(0)?.icon!!,dta.templateBtn?.expire?.get(0)?.textKey!!,
            dta.templateBtn?.expire?.get(1)?.state!!,dta.templateBtn?.expire?.get(1)?.icon!!,dta.templateBtn?.expire?.get(1)?.textKey!!)
        else checkEnableOneButton(viewGroup,
            dta.templateBtn?.expire?.get(0)?.state!!,dta.templateBtn?.expire?.get(0)?.icon!!,dta.templateBtn?.expire?.get(0)?.textKey!!)


        if(dta.templateBtn?.fail?.size!!>1)
            checkEnableTwoButton(viewGroup,
            dta.templateBtn?.fail?.get(0)?.state!!,dta.templateBtn?.fail?.get(0)?.icon!!,dta.templateBtn?.fail?.get(0)?.textKey!!,
            dta.templateBtn?.fail?.get(1)?.state!!,dta.templateBtn?.fail?.get(1)?.icon!!,dta.templateBtn?.fail?.get(1)?.textKey!!)
        else checkEnableOneButton(viewGroup,
            dta.templateBtn?.fail?.get(0)?.state!!,dta.templateBtn?.fail?.get(0)?.icon!!,dta.templateBtn?.fail?.get(0)?.textKey!!)


        if(dta.templateBtn?.cancel?.size!!>1)
            checkEnableTwoButton(viewGroup,
            dta.templateBtn?.cancel?.get(0)?.state!!,dta.templateBtn?.cancel?.get(0)?.icon!!,dta.templateBtn?.cancel?.get(0)?.textKey!!,
            dta.templateBtn?.fail?.get(1)?.state!!,dta.templateBtn?.cancel?.get(1)?.icon!!,dta.templateBtn?.cancel?.get(1)?.textKey!!)
        else checkEnableOneButton(viewGroup,
            dta.templateBtn?.cancel?.get(0)?.state!!,dta.templateBtn?.cancel?.get(0)?.icon!!,dta.templateBtn?.cancel?.get(0)?.textKey!!)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun checkEnableTwoButton(viewGroup: ViewGroup, state1: String, icon1: String, textKey1: String, state2: String, icon2: String, textKey2: String) {
      if (state1.equals("enable")==true && state2.equals("enable")==true && flag ==0){
          flag =1
          setTwoButton(viewGroup,icon1,textKey1,icon2,textKey2)
      }
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun checkEnableOneButton(viewGroup: ViewGroup, state: String, icon: String, textKey: String) {
        if (state.equals("enable")==true&& flag ==0){
            flag =1
            setOneButton(viewGroup,icon,textKey)
        }
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun setOneButton(viewGroup: ViewGroup, icon: String, textKey: String) {
        val linearLayout :LinearLayout = LinearLayout(context)
        val paramParent = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT,1f)
        paramParent.setMargins(30,0,30,20)
        linearLayout.layoutParams = paramParent
        linearLayout.gravity = Gravity.CENTER_HORIZONTAL
        linearLayout.orientation = LinearLayout.HORIZONTAL

        val parambt = LinearLayout.LayoutParams(150,150)
        val paramtext = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
        paramtext.setMargins(0,30,0,0)
        val requestOptions = RequestOptions()

        val button : ImageView = ImageView(context)
        button.layoutParams = parambt
        button.scaleType = ImageView.ScaleType.FIT_CENTER
        requestOptions.placeholder(R.mipmap.ic_launcher)
        Glide.with(context).setDefaultRequestOptions(requestOptions).load(icon).into(button);
        button.setOnClickListener(View.OnClickListener {
            Toast.makeText(context,"action",Toast.LENGTH_SHORT).show()
        })

        val textView : TextView = TextView(context)
        textView.layoutParams = paramtext
        textView.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
        textView.setText(textKey)
        textView.setOnClickListener(View.OnClickListener {
            Toast.makeText(context,"action",Toast.LENGTH_SHORT).show()
        })

        linearLayout.addView(button)
        linearLayout.addView(textView)

        viewGroup.addView(linearLayout)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun setTwoButton(viewGroup: ViewGroup, icon1: String, textKey1: String, icon2: String, textKey2: String) {
        val linearLayoutParent :LinearLayout = LinearLayout(context)
        val paramParent = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT,1.5f)
        paramParent.setMargins(30,0,30,20)
        linearLayoutParent.layoutParams = paramParent
        linearLayoutParent.orientation = LinearLayout.HORIZONTAL

        val linearLayoutParent1 : LinearLayout = LinearLayout(context)
        linearLayoutParent1.layoutParams = paramParent
        linearLayoutParent1.orientation = LinearLayout.HORIZONTAL

        val linearLayoutParent2 : LinearLayout = LinearLayout(context)
        linearLayoutParent2.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT,2f)

        val linearLayoutParent3 : LinearLayout = LinearLayout(context)
        linearLayoutParent3.layoutParams = paramParent
        linearLayoutParent3.orientation = LinearLayout.HORIZONTAL

        val parambt = LinearLayout.LayoutParams(150,150)
        val paramtext = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
        paramtext.setMargins(0,20,0,0)
        val requestOptions = RequestOptions()

        val button1 : ImageView = ImageView(context)
        button1.layoutParams = parambt
        button1.scaleType = ImageView.ScaleType.FIT_CENTER
        requestOptions.placeholder(R.drawable.btn_cancel)
        Glide.with(context).setDefaultRequestOptions(requestOptions).load(icon1).into(button1);
        button1.setOnClickListener(View.OnClickListener {
            Toast.makeText(context,"cancel",Toast.LENGTH_SHORT).show()
        })

        val textView1 : TextView = TextView(context)
        textView1.layoutParams = paramtext
        textView1.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
        textView1.setText(textKey1)
        textView1.setOnClickListener(View.OnClickListener {
            Toast.makeText(context,"cancel",Toast.LENGTH_SHORT).show()
        })

        val textView2 : TextView = TextView(context)
        textView2.layoutParams = paramtext
        textView2.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
        textView2.setText(textKey2)
        textView2.setOnClickListener(View.OnClickListener {
            Toast.makeText(context,"comfig",Toast.LENGTH_SHORT).show()
        })

        val button2 : ImageView = ImageView(context)
        button2.layoutParams = parambt
        button2.scaleType = ImageView.ScaleType.FIT_CENTER
        requestOptions.placeholder(R.drawable.btn_confirm)
        Glide.with(context).setDefaultRequestOptions(requestOptions).load(icon2).into(button2);
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