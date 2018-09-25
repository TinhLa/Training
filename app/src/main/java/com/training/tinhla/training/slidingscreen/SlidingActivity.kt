package com.training.tinhla.training.slidingscreen

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.constraint.ConstraintLayout
import android.support.v4.widget.NestedScrollView
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import com.training.tinhla.training.R
import com.training.tinhla.training.basemodel.BaseActivity
import com.training.tinhla.training.commonmodel.Template
import com.training.tinhla.training.databinding.ActivitySlidingBinding
import com.training.tinhla.training.slidingscreen.adapter.SlidingAdapter
import com.training.tinhla.training.slidingscreen.model.Post
import com.training.tinhla.training.slidingscreen.viewmodel.IframeViewModel
import javax.inject.Inject

class SlidingActivity : BaseActivity(), SlidingInterface.view {
    private var emulator : Int = 250
    private var device : Int = 150
    private lateinit var bind: ActivitySlidingBinding
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateFrameHeader(template: Template) {
        IframeViewModel(bind, template)
    }

    @Inject
    lateinit var presenter: SlidingInterface.presenter
    @Inject
    lateinit var adapter: SlidingAdapter
    override fun success(posts: List<Post>) {
        Log.d("thanh cong", posts.toString())
        adapter.setListPost(posts)
    }

    override fun error(mes: String) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_sliding)
        val metrics = resources.displayMetrics
        bind.slidingLayout.panelHeight = metrics.heightPixels - 350
        val param = bind.slidingLayout.layoutParams as ConstraintLayout.LayoutParams
        param.topMargin = 200
        bind.slidingLayout.layoutParams = param
        presenter.getJsonTemplate()

    }
}