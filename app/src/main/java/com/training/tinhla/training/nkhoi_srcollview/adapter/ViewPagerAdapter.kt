package com.training.tinhla.training.nkhoi_srcollview.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.training.tinhla.training.R

class ViewPagerAdapter(var context: Context, arrayImage: List<String>?) : PagerAdapter() {

    lateinit var inflater: LayoutInflater
             var array : List<String>

    init {
        array = if(arrayImage===null) listOf("null") else arrayImage
    }
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
     return array.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(R.layout.item_image_property,container,false)
        val img : ImageView = view.findViewById(R.id.img_iframe_property)
        Glide.with(context).load(array[position]).into(img)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }


}