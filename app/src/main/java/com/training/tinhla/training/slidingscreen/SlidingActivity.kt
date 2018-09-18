package com.training.tinhla.training.slidingscreen

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.training.tinhla.training.R
import com.training.tinhla.training.R.id.sv_parent
import com.training.tinhla.training.basemodel.BaseActivity
import com.training.tinhla.training.slidingscreen.adapter.SlidingAdapter
import com.training.tinhla.training.slidingscreen.model.Post
import javax.inject.Inject

class SlidingActivity : BaseActivity(), SlidingInterface.view {
    @Inject
    lateinit var presenter: SlidingInterface.presenter
    @Inject
    lateinit var adapter: SlidingAdapter
    lateinit var recycle: RecyclerView
    override fun success(posts: List<Post>) {
        Log.d("thanh cong", posts.toString())
        adapter.setListPost(posts)
    }

    override fun error(mes: String) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sliding)
        presenter.getListPost()
        recycle = findViewById<View>(R.id.lv_list) as RecyclerView
        recycle.adapter = adapter
        val layoutManager = LinearLayoutManager(this)
        recycle.layoutManager = layoutManager

        val metrics = resources.displayMetrics
        val params = recycle.layoutParams
        params.height = (metrics.heightPixels)-200
        recycle.layoutParams = params
        initScroll(layoutManager)

    }

    @TargetApi(Build.VERSION_CODES.M)
    @SuppressLint("ClickableViewAccessibility")
    private fun initScroll(layoutManager: LinearLayoutManager) {
        val svParent = findViewById<View>(R.id.sv_parent) as NestedScrollView
        svParent.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == (v.getChildAt(0).measuredHeight - v.measuredHeight)) {
                Log.d("scroll", "sroll bottom," + scrollY)
                recycle.isNestedScrollingEnabled = true
                svParent.setOnTouchListener { _, _ -> true }
            } else {
                recycle.isNestedScrollingEnabled = false
                svParent.setOnTouchListener { _, _ -> false }
            }
        })
        recycle.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (layoutManager.findFirstCompletelyVisibleItemPosition() == 0) {
                    svParent.setOnTouchListener { _, _ -> false }
                    recycle.isNestedScrollingEnabled = false
                } else {
                    svParent.setOnTouchListener { _, _ -> true }
                    recycle.isNestedScrollingEnabled = true
                }
            }
        })
    }
}