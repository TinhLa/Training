package com.training.tinhla.training.slidingscreen.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.training.tinhla.training.R
import com.training.tinhla.training.databinding.ItemSlidingBinding
import com.training.tinhla.training.slidingscreen.model.Post
import com.training.tinhla.training.slidingscreen.viewmodel.SlidingModel
import java.util.*

class SlidingAdapter : RecyclerView.Adapter<SlidingAdapter.SlidingViewHoder>() {
    private var mlistPost: List<Post> = Collections.emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlidingViewHoder {
        return SlidingViewHoder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_sliding,parent,false))
    }

    override fun getItemCount(): Int = mlistPost.size

    override fun onBindViewHolder(holder: SlidingViewHoder, position: Int) {
        holder.bind(mlistPost[position])
    }
    fun setListPost(post: List<Post>){
        this.mlistPost = post
        notifyDataSetChanged()
    }

    class SlidingViewHoder(itemSlidingBinding: ItemSlidingBinding) : RecyclerView.ViewHolder(itemSlidingBinding.root){
        private val mitem = itemSlidingBinding
        fun bind(post: Post){
            if (mitem.itemsliding==null)mitem.itemsliding = SlidingModel(itemView.context,post)
            else mitem.itemsliding!!.setPost(post)
            mitem.executePendingBindings()
        }
    }
}