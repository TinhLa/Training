package com.training.tinhla.training.splashscreen.setup_sliding_up_panel

import android.annotation.SuppressLint
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import com.training.tinhla.training.base.custom_view.LockableScrollView
import com.training.tinhla.training.splashscreen.OnTouchHeaderIFrame


/*
* Functions of this class have not edited completely to readable
* I commited and pushed to edit it at home
* */
class ScrollInstaller(var slidingPanelLayout:SlidingUpPanelLayout, var headerIFrame:LinearLayout, var mainViewSlidingPanelLayout:LinearLayout, var childScrollView:LockableScrollView) {

    // y coordinate of touched position on child ScrollView
    var oldY = -1f

    // flag indicate whether child ScrollView is scrolling up
    var isScrollingUp = false

    // flag indicate whether SlidingUpPanel is expanding
    var expanding = false

    var timeStart:Long=0L

    // 2 variables contain location of SlidingUpPanel and child ScrollView
    var parentLocs = intArrayOf(0,0)
    var childLocs = intArrayOf(0,0)

    // flag indicate whether need to scroll down child ScrollView programmatically
    var needScrollDownChildBeforeSlidePanel = false

    // y coordinate of touch position on SlidingUpPanel
    var pOldY = -1f

    // velocity of scroll child ScrollView
    val SLIDE_VELOCITY = 15

    // main install function
    fun installScrollProcess(imagesViewPager:ViewPager) {
        // lock child ScrollView on start
        if (slidingPanelLayout.panelState == SlidingUpPanelLayout.PanelState.COLLAPSED) {
            disableChildScrollView()
        }

        childScrollView.viewTreeObserver.addOnScrollChangedListener({
            // ScrollView is scrolling up and touch the top
            collapsedSlidingUpPanel(isScrollingUp)
        })

        setupSlideListener()

        // setup listener for HeaderIFrame ViewGroup
        headerIFrame.setOnTouchListener(OnTouchHeaderIFrame(imagesViewPager, slidingPanelLayout))

        mainViewSlidingPanelLayout.setOnTouchListener (OnTouchHeaderIFrame(imagesViewPager, slidingPanelLayout))
    }

    var collapsing = false

    private fun collapsedSlidingUpPanel(isScrollingUp:Boolean) {
        if (childScrollView.scrollY == 0 && isScrollingUp) {
            // slide down the Panel
            slidingPanelLayout.isTouchEnabled = true
            childScrollView.scrollable = false
            collapsing = true
            Log.d("LOG", "collapsing")
            slidingPanelLayout.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupSlideListener() {
        setupOnTouchSlidingUpPanel()

        slidingPanelLayout.addPanelSlideListener(object: SlidingUpPanelLayout.PanelSlideListener{
            override fun onPanelSlide(panel: View?, slideOffset: Float) {
            }

            override fun onPanelStateChanged(view: View?, previousState: SlidingUpPanelLayout.PanelState?, newState: SlidingUpPanelLayout.PanelState?) {

                if (previousState == SlidingUpPanelLayout.PanelState.COLLAPSED
                        && newState == SlidingUpPanelLayout.PanelState.DRAGGING) {
                    expanding = true
                    timeStart = System.currentTimeMillis()
                }
                else if (newState == SlidingUpPanelLayout.PanelState.DRAGGING
                        && (previousState == SlidingUpPanelLayout.PanelState.EXPANDED
                                || previousState == SlidingUpPanelLayout.PanelState.ANCHORED)) {

                    childScrollView.smoothScrollTo(0,0)
                }
                // when panel expand
                else if (previousState == SlidingUpPanelLayout.PanelState.DRAGGING
                        && (newState == SlidingUpPanelLayout.PanelState.EXPANDED)) {
                    expanding = false

                    // disable slidable of Sliding Up Panel Layout
                    slidingPanelLayout.isTouchEnabled = false

                    enableChildScrollView()

                    // set listener for ScrollView
                    // to check if it is scrolling up
                    childScrollView.setOnTouchListener({ _, event ->
                        when (event?.action) {
                            MotionEvent.ACTION_DOWN -> {
                                oldY = event?.y ?: -1f
                                isScrollingUp = false
                            }

                            MotionEvent.ACTION_MOVE -> {
                                // check if direction of scrolling (up or not)
                                // and collapsed SlidingUpPanel when it is scroll at top
                                calculateScrollState(event?.y ?: -1f)
                            }
                        }

                        false
                    })
                }
                // disable ScrollView if Panel start sliding down
                else if (newState == SlidingUpPanelLayout.PanelState.COLLAPSED ||
                        newState == SlidingUpPanelLayout.PanelState.EXPANDED || newState == SlidingUpPanelLayout.PanelState.ANCHORED) {

                    disableChildScrollView()
                }
            }
        })
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupOnTouchSlidingUpPanel() {
        slidingPanelLayout.setOnTouchListener{_, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    pOldY = event?.y?:-1f
                    needScrollDownChildBeforeSlidePanel = false
                    collapsing = false
                }

                MotionEvent.ACTION_MOVE -> {
                    if (needScrollDownChildBeforeSlidePanel) {

                        if (!collapsing) {
                            var deltaY = (event?.y?:-1f) - pOldY
                            pOldY = event?.y?:-1f

                            // scroll up child ScrollView
                            if (deltaY.compareTo(0f).equals(1)) {

                                childScrollView.smoothScrollBy(0, -1 * SLIDE_VELOCITY - 5)
                            }
                            // scroll down child ScrollView
                            else{
                                childScrollView.smoothScrollBy(0,SLIDE_VELOCITY)
                            }
                        }

                    }else{
                        childScrollView.getLocationOnScreen(childLocs)
                        slidingPanelLayout.getLocationOnScreen(parentLocs)

                        if (childLocs[1] == parentLocs[1] && expanding) {
                            var deltaY = (event?.y?:-1f) - pOldY
                            pOldY = event?.y?:-1f
                            //scroll up
                            if (deltaY.compareTo(0f).equals(-1)) {
                                var scrollY = childScrollView.scrollY
                                childScrollView.smoothScrollTo(0, scrollY+SLIDE_VELOCITY)

                                slidingPanelLayout.isTouchEnabled = false

                                needScrollDownChildBeforeSlidePanel = true
                            }
                        }
                    }
                }
            }

            false
        }
    }

    private fun calculateScrollState(y: Float) {
        var deltaY = y.minus((oldY))

        // it is scrolling up
        if (deltaY.compareTo(0f).equals(1)) {
            isScrollingUp = true
        }else{
            isScrollingUp = false
        }

        collapsedSlidingUpPanel(isScrollingUp)
    }

    fun enableChildScrollView() {
        childScrollView.scrollable = true
    }

    fun disableChildScrollView() {
        childScrollView.scrollable = false
    }

}