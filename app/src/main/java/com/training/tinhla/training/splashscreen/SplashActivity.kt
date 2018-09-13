package com.training.tinhla.training.splashscreen

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ScrollView
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import com.training.tinhla.training.R
import com.training.tinhla.training.basemodel.BaseActivity
import kotlinx.android.synthetic.main.content_splash.*
import kotlinx.android.synthetic.main.layout_panel_in_layout_sliding_up_panel.*
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashInterface.View {
    @Inject
    lateinit var presenter: SplashInterface.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        setupSlidingUpPanelLayout()
    }

    private fun setupSlidingUpPanelLayout() {

        var oldY = -1f
        var isScrollingUp = false

        // set anchor for panel when it slide up
        sliding_layout.anchorPoint = 0.8f

        if(sliding_layout.panelState == SlidingUpPanelLayout.PanelState.COLLAPSED)
            sv_main.setOnTouchListener(View.OnTouchListener { v, event -> true })

        sv_main.viewTreeObserver.addOnScrollChangedListener(ViewTreeObserver.OnScrollChangedListener {
            // ScrollView is scrolling up and touch the top
            if (sv_main.scrollY == 0 && isScrollingUp) {
                // slide down the Panel
                sliding_layout.isTouchEnabled = true
                sliding_layout.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
            }
        })

        sliding_layout.addPanelSlideListener(object: SlidingUpPanelLayout.PanelSlideListener{
            override fun onPanelSlide(panel: View?, slideOffset: Float) {
                sv_main.setOnTouchListener(View.OnTouchListener { v, event -> false })
            }

            override fun onPanelStateChanged(panel: View?, previousState: SlidingUpPanelLayout.PanelState?, newState: SlidingUpPanelLayout.PanelState?) {

                // scroll a little when panel is sliding up
                if (previousState == SlidingUpPanelLayout.PanelState.COLLAPSED && newState == SlidingUpPanelLayout.PanelState.DRAGGING) {
                    sv_main.smoothScrollTo(0, 30)
                }
                // when panel expand
                else if (previousState == SlidingUpPanelLayout.PanelState.DRAGGING
                        && (newState == SlidingUpPanelLayout.PanelState.EXPANDED || newState == SlidingUpPanelLayout.PanelState.ANCHORED)) {
                    // disable slidable of Sliding Up Panel Layout
                    sliding_layout.isTouchEnabled = false

                    // set listener for ScrollView
                    // to check if it is scrolling up
                    sv_main.setOnTouchListener(object: View.OnTouchListener{
                        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                            when (event?.action) {
                                MotionEvent.ACTION_DOWN -> oldY = event!!.y

                                MotionEvent.ACTION_MOVE -> {
                                    var deltaY = event!!.y.minus((oldY))

                                    // it is scrolling up
                                    if (deltaY.compareTo(0f).equals(1)) {
                                        isScrollingUp = true
                                    }else{
                                        isScrollingUp = false
                                    }
                                }
                            }

                            return false
                        }
                    })
                }
                // disable ScrollView if Panel start sliding down
                else if (previousState == SlidingUpPanelLayout.PanelState.ANCHORED && newState == SlidingUpPanelLayout.PanelState.DRAGGING) {
                    sv_main.setOnTouchListener(View.OnTouchListener { v, event -> true })
                }
            }
        })
    }

    override fun getAppContext(): Context {
        return app
    }
}
