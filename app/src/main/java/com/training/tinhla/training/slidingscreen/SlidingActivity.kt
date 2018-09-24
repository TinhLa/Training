package com.training.tinhla.training.slidingscreen

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.widget.NestedScrollView
import android.util.Log
import android.view.View
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
    private lateinit var svChild: NestedScrollView
    private lateinit var svParent: NestedScrollView
    override fun success(posts: List<Post>) {
        Log.d("thanh cong", posts.toString())
        adapter.setListPost(posts)
    }

    override fun error(mes: String) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_sliding)
//        presenter.getListPost()
        presenter.getJsonTemplate()
        svParent = findViewById<View>(R.id.sv_parent) as NestedScrollView
        svChild = findViewById<View>(R.id.sv_list) as NestedScrollView
//        recycle.adapter = adapter
//        val layoutManager = LinearLayoutManager(this)
//        recycle.layoutManager = layoutManager

        val metrics = resources.displayMetrics
        val params = svChild.layoutParams
        params.height = (metrics.heightPixels) - emulator
        svChild.layoutParams = params
        initScroll()

    }

//    @TargetApi(Build.VERSION_CODES.M)
//    @SuppressLint("ClickableViewAccessibility")
//    private fun initScroll(layoutManager: LinearLayoutManager) {
//        val svParent = findViewById<View>(R.id.sv_parent) as NestedScrollView
//        svParent.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
//            if (scrollY == (v.getChildAt(0).measuredHeight - v.measuredHeight)) {
//                Log.d("scroll", "sroll bottom," + scrollY)
//                recycle.isNestedScrollingEnabled = true
//                svParent.setOnTouchListener { _, _ -> true }
//            } else {
//                recycle.isNestedScrollingEnabled = false
//                svParent.setOnTouchListener { _, _ -> false }
//            }
//        })
//        recycle.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                if (layoutManager.findFirstCompletelyVisibleItemPosition() == 0) {
//                    svParent.setOnTouchListener { _, _ -> false }
//                    recycle.isNestedScrollingEnabled = false
//                } else {
//                    svParent.setOnTouchListener { _, _ -> true }
//                    recycle.isNestedScrollingEnabled = true
//                }
//            }
//        })
//    }

    @TargetApi(Build.VERSION_CODES.M)
    @SuppressLint("ClickableViewAccessibility")
    private fun initScroll() {
        svChild.setOnTouchListener { _, _ -> true }
        svParent.setOnTouchListener { _, _ -> false }
        svParent.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
            if (scrollY == (v.getChildAt(0).measuredHeight - v.measuredHeight)) {
                svChild.setOnTouchListener { _, _ -> false }
                svParent.setOnTouchListener { _, _ -> true }
            } else {
                svChild.setOnTouchListener { _, _ -> true }
                svParent.setOnTouchListener { _, _ -> false }
            }
        })
        svChild.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, Y, _, _ ->
            if (Y == 0) {
                svChild.setOnTouchListener { _, _ -> true }
                svParent.setOnTouchListener { _, _ -> false }
            } else {
                svChild.setOnTouchListener { _, _ -> false }
                svParent.setOnTouchListener { _, _ -> true }
            }
        })
    }
}