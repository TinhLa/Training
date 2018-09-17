package com.training.tinhla.training.nkhoi_srcollview.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.training.tinhla.training.R
import com.training.tinhla.training.nkhoi_srcollview.DBListText


class recyclerAdapter(var context:Context, array : ArrayList<Int>) : RecyclerView.Adapter<recyclerAdapter.ViewHolder>() {
     val arrayList: ArrayList<Int>  = array

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater : LayoutInflater = LayoutInflater.from(parent.context)
        val itemView : View = layoutInflater.inflate(R.layout.item_image_property,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
      return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
           holder.img.setImageResource(arrayList.get(position))
    }


    class  ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
     val img  = itemView.findViewById(R.id.img_property) as ImageView
    }
}