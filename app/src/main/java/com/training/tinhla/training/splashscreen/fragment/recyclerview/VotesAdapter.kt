package com.training.tinhla.training.splashscreen.fragment.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.training.tinhla.training.splashscreen.fragment.Votes
import javax.inject.Inject

import com.training.tinhla.training.R

class VotesAdapter (var list:List<Votes>) : RecyclerView.Adapter<ItemVotesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_votes, parent, false)

        return ItemVotesViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ItemVotesViewHolder, position: Int) {
        holder.binds(list.get(position))
    }
}