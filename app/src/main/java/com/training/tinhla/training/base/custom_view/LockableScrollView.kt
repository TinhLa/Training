package com.training.tinhla.training.base.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ScrollView

class LockableScrollView : ScrollView {
    constructor(context:Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    var scrollable:Boolean = true

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.action) {
            MotionEvent.ACTION_DOWN ->
                return scrollable && super.onTouchEvent(ev)

            else ->
                return super.onTouchEvent(ev)
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return scrollable && super.onInterceptTouchEvent(ev)
    }
}