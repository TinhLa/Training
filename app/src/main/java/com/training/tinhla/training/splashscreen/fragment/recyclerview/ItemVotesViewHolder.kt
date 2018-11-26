package com.training.tinhla.training.splashscreen.fragment.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.training.tinhla.training.splashscreen.fragment.Votes
import kotlinx.android.synthetic.main.item_votes.view.*

import com.training.tinhla.training.R

class ItemVotesViewHolder(view:View) : RecyclerView.ViewHolder(view) {
    private lateinit var voterNameTv: TextView
    private lateinit var voteBtn : Button

    init {
        voterNameTv = view.findViewById(R.id.voter_name)
        voteBtn = view.findViewById(R.id.btn_vote)
    }

    fun binds(item:Votes){
        voterNameTv.setText(item.name)
        voteBtn.setText("Vote: " + item.votedCount)
    }
}