package com.training.tinhla.training.splashscreen.setup_sliding_up_panel

import android.annotation.SuppressLint
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import com.training.tinhla.training.splashscreen.OnTouchHeaderIFrame


/*
* Functions of this class have not edited completely to readable
* I commited and pushed to edit it at home
* */
class ScrollInstaller(var slidingPanelLayout:SlidingUpPanelLayout, var headerIFrame:LinearLayout, var mainViewSlidingPanelLayout:LinearLayout, var childScrollView:ScrollView) {

    // y coordinate of touched position on child ScrollView
    var oldY = -1f

    // flag indicate whether child ScrollView is scrolling up
    var isScrollingUp = false

    // main install function
    fun installScrollProcess(imagesViewPager:ViewPager) {
        childScrollView.viewTreeObserver.addOnScrollChangedListener({
            // ScrollView is scrolling up and touch the top
            collapsedSlidingUpPanel(isScrollingUp)
        })

        setupSlideListener()

        // setup listener for HeaderIFrame ViewGroup
        headerIFrame.setOnTouchListener(OnTouchHeaderIFrame(imagesViewPager, slidingPanelLayout))

        mainViewSlidingPanelLayout.setOnTouchListener (OnTouchHeaderIFrame(imagesViewPager, slidingPanelLayout))
    }

    private fun collapsedSlidingUpPanel(isScrollingUp:Boolean) {
        if (childScrollView.scrollY == 0 && isScrollingUp) {
            slidingPanelLayout.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupSlideListener() {
        slidingPanelLayout.addPanelSlideListener(object: SlidingUpPanelLayout.PanelSlideListener{
            override fun onPanelSlide(panel: View?, slideOffset: Float) {
            }

            override fun onPanelStateChanged(view: View?, previousState: SlidingUpPanelLayout.PanelState?, newState: SlidingUpPanelLayout.PanelState?) {

                if (newState == SlidingUpPanelLayout.PanelState.DRAGGING
                        && (previousState == SlidingUpPanelLayout.PanelState.EXPANDED
                                || previousState == SlidingUpPanelLayout.PanelState.ANCHORED)) {

                    childScrollView.smoothScrollTo(0,0)
                }
                // when panel expand
                else if (previousState == SlidingUpPanelLayout.PanelState.DRAGGING
                        && (newState == SlidingUpPanelLayout.PanelState.EXPANDED)) {

                    // set listener for ScrollView
                    // to check if it is scrolling up
                    childScrollView.setOnTouchListener({ _, event ->
                        when (event?.action) {
                            MotionEvent.ACTION_DOWN -> {
                                oldY = event.y ?: -1f
                                isScrollingUp = false
                            }

                            MotionEvent.ACTION_MOVE -> {
                                // check if direction of scrolling (up or not)
                                // and collapsed SlidingUpPanel when it is scroll at top
                                calculateScrollState(event.y ?: -1f)
                            }
                        }

                        false
                    })
                }
            }
        })
    }

    private fun calculateScrollState(y: Float) {
        val deltaY = y.minus((oldY))

        // it is scrolling up
        if (deltaY.compareTo(0f).equals(1)) {
            isScrollingUp = true
        }else{
            isScrollingUp = false
        }

        collapsedSlidingUpPanel(isScrollingUp)
    }
}