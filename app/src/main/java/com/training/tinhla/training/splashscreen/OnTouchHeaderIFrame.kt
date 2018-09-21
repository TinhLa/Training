package com.training.tinhla.training.splashscreen

import android.graphics.Rect
import android.support.v4.view.VelocityTrackerCompat
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import com.training.tinhla.training.base.custom_view.LockableScrollView

/**
 * Listener switch page of Background Images ViewPager when user swipe horizontally on HeaderIFrame ViewGroup
 */
class OnTouchHeaderIFrame(var viewPager:ViewPager, var slidingUpPanel:SlidingUpPanelLayout) : View.OnTouchListener {

    var oldX = 0f
    var oldY = 0f
    var verticalSwipe:Boolean = false
    var velocityTracker:VelocityTracker?=null
    var check = false
    var needExpandPanelOnTouchUp = false

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {

        var index = event?.actionIndex
        var pointerID:Int = 0
        if (index != null) {
            pointerID = event?.getPointerId(index)?:0
        }

        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                oldX = event.x
                oldY = event.y

                if(velocityTracker != null){
                    velocityTracker!!.clear()
                }else{
                    velocityTracker = VelocityTracker.obtain()
                }
                velocityTracker!!.addMovement(event)

                check = true
            }

            MotionEvent.ACTION_MOVE -> {
                var deltaX = event.x.minus(oldX)
                var deltaY = event.y.minus(oldY)


                var absDeltaX = Math.abs(deltaX)
                var absDeltaY = Math.abs(deltaY)

                if (absDeltaX > absDeltaY) {
                    verticalSwipe = false
                    // swipe background images
                    swipeBgImages(deltaX)
                } else {
                    if (deltaY.compareTo(0).equals(-1)) {
                        var anchorPoint = slidingUpPanel.anchorPoint
                        if (anchorPoint.equals(1f)) {
                            anchorPoint = 0.1f
                        }else{
                            anchorPoint += 0.1f
                        }

                        slidingUpPanel.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
                        needExpandPanelOnTouchUp = true
                        return false
                    }
                }
            }

            MotionEvent.ACTION_UP -> {
                if (needExpandPanelOnTouchUp) {
                    slidingUpPanel.panelState = SlidingUpPanelLayout.PanelState.EXPANDED
                    needExpandPanelOnTouchUp = false
                }
            }
        }

        return true
    }

    private fun swipeBgImages(deltaX: Float) {
        var pager:Int = viewPager.currentItem

        // swipe right to left
        if (deltaX.compareTo(-10f).equals(-1)) {
            if (pager < viewPager.childCount - 1) {
                pager++
            }else{
                pager = 0
            }
        }
        // swipe left to right
        else if(deltaX.compareTo(10f).equals(1)){
            if (pager > 0) {
                pager--
            }else{
                pager = viewPager.childCount-1
            }
        }else{
            return
        }

        viewPager.setCurrentItem(pager)
    }

    private fun scrollViews() {
        slidingUpPanel.panelState = SlidingUpPanelLayout.PanelState.EXPANDED
    }
}