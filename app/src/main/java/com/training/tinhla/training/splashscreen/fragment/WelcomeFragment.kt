package com.training.tinhla.training.splashscreen.fragment


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.training.tinhla.training.R
import com.training.tinhla.training.basemodel.BaseFragment
import com.training.tinhla.training.splashscreen.fragment.recyclerview.VotesAdapter
import kotlinx.android.synthetic.main.fragment_welcome.*
import javax.inject.Inject

class WelcomeFragment @Inject constructor() : BaseFragment(), WelcomeInterface.View {

    companion object {
        private var INSTANCE : WelcomeFragment ?= null

        fun newInstance() : WelcomeFragment {
            if (INSTANCE == null) {
                INSTANCE = WelcomeFragment()
            }
            return INSTANCE!!
        }
    }

    override fun _getContext(): Context {
        return activity!!.baseContext
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = ArrayList<Votes>()
        list.add(Votes("Annie", 2))
        list.add(Votes("Annie", 2))
        list.add(Votes("Annie", 2))
        list.add(Votes("Annie", 2))
        val adapter = VotesAdapter(list)
        rv_survey.adapter = adapter
        rv_survey.setHasFixedSize(true)
        rv_survey.layoutManager = LinearLayoutManager(app, LinearLayoutManager.VERTICAL, false)

    }
}