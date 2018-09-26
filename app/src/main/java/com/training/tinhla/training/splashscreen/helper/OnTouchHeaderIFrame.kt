package com.training.tinhla.training.splashscreen.helper

import android.graphics.Rect
import android.support.v4.view.VelocityTrackerCompat
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import com.sothree.slidinguppanel.SlidingUpPanelLayout

/**
 * Listen event switch page of Background Images ViewPager when user swipe horizontally on HeaderIFrame ViewGroup
 * and event scroll up to expand panel
 */
class OnTouchHeaderIFrame(var viewPager:ViewPager, var slidingUpPanel:SlidingUpPanelLayout) : View.OnTouchListener {

    var oldX = 0f
    var oldY = 0f
    var verticalSwipe:Boolean = false
    var needExpandPanelOnTouchUp = false

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {

        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                oldX = event.x
                oldY = event.y
            }

            MotionEvent.ACTION_MOVE -> {
                return onSwiping(event)
            }

            MotionEvent.ACTION_UP -> {
                // expand the panel if needed
                if (needExpandPanelOnTouchUp) {
                    slidingUpPanel.panelState = SlidingUpPanelLayout.PanelState.EXPANDED
                    needExpandPanelOnTouchUp = false
                }
            }
        }

        return true
    }

    // Check if swipe horizontally or vertically
    private fun onSwiping(event: MotionEvent) : Boolean {
        val deltaX = event.x.minus(oldX)
        val deltaY = event.y.minus(oldY)


        val absDeltaX = Math.abs(deltaX)
        val absDeltaY = Math.abs(deltaY)

        val absDelta = Math.max(absDeltaX, absDeltaY)
        // only seem as swipe if the delta is enough big
        if (absDelta > 10) {
            if (absDeltaX > absDeltaY) {
                verticalSwipe = false
                // swipe background images
                swipeBgImages(deltaX)
            } else {
                if (deltaY.compareTo(0).equals(-1)) {

                    slidingUpPanel.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
                    needExpandPanelOnTouchUp = true
                    return false
                }
            }
        }

        return true
    }

    // Swipe background images of header IFrame
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
}