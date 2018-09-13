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
import com.training.tinhla.training.R
import com.training.tinhla.training.basemodel.BaseActivity
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashInterface.View {
    @Inject
    lateinit var presenter: SplashInterface.Presenter

    lateinit var svParent: ScrollView
    lateinit var svChild : NestedScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        svParent = findViewById(R.id.sv_parent)
        svChild = findViewById(R.id.sv_child)

        var childLocs = intArrayOf(0,0)
        var oldY = -1f

        var pRects = Rect()


        svParent.viewTreeObserver.addOnScrollChangedListener(ViewTreeObserver.OnScrollChangedListener {
            // determine location
            svChild.getLocationInWindow(childLocs)

            if(childLocs[1] <= 100){
                svParent.isEnabled = false
                svParent.scrollTo(0,500)
            }
        })

        var oldY_parent = -1f

        svParent.setOnTouchListener(object : View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {

                if (svChild.scrollY > 0) {
                    svParent.requestDisallowInterceptTouchEvent(true)
                    return true
                }

                svParent.getLocalVisibleRect(pRects)

                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> oldY_parent = event?.y

                    MotionEvent.ACTION_MOVE -> {
                        if (!oldY_parent.equals(-1f)) {

                            if (pRects.top >= 500) {
                                var deltaY = event!!.y.minus(oldY_parent)

                                //scroll down
                                if (deltaY.compareTo(0f).equals(1)) {

                                    if (svChild.scrollY == 0) {
                                        svParent.requestDisallowInterceptTouchEvent(false)
                                        return false
                                    }
                                }

                                svParent.requestDisallowInterceptTouchEvent(true)
                                return true
                            }
                        }
                        oldY_parent = event?.y
                    }

                    MotionEvent.ACTION_UP -> oldY_parent = -1f
                }

                svParent.requestDisallowInterceptTouchEvent(false)

                return false
            }
        })

        svChild.setOnTouchListener(object: View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//                svChild.parent.requestDisallowInterceptTouchEvent(true)

                svParent.getLocalVisibleRect(pRects)
                if (pRects.top >= 500) {


                    when (event?.action) {
                        MotionEvent.ACTION_DOWN -> oldY = event?.y

                        MotionEvent.ACTION_MOVE -> {
                            if (!oldY.equals(-1f)) {

                                var deltaY = event!!.y.minus(oldY)

                                //scroll up
                                if (deltaY.compareTo(0f).equals(1)) {

                                    if (svChild.scrollY == 0) {
                                        svParent.requestDisallowInterceptTouchEvent(false)
                                        return true
                                    }
                                }

                                svChild.parent.requestDisallowInterceptTouchEvent(true)
                                return false
                            }
                        }

                        MotionEvent.ACTION_UP -> oldY = -1f
                    }
                }

                return false
            }
        })
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        Log.d("LOG", "height of action bar: " + supportActionBar?.height)

        val gvMain = findViewById<View>(R.id.main_gv)
        svChild.layoutParams.height = gvMain.height

        var locs = intArrayOf(0,0)
        svParent.getLocationOnScreen(locs)
        Log.d("LOG", "locs: " + locs[1])


        svParent.getLocationInWindow(locs)
        Log.d("LOG", "locs: " + locs[1])
    }

    override fun getAppContext(): Context {
        return app
    }
}
