package com.training.tinhla.training.splashscreen

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ScrollView
import com.training.tinhla.training.R
import com.training.tinhla.training.basemodel.BaseActivity
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashInterface.View {
    @Inject
    lateinit var presenter: SplashInterface.Presenter

    lateinit var sv_parent: ScrollView
    lateinit var sv_child : ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        sv_parent = findViewById(R.id.sv_parent)
        sv_child = findViewById(R.id.sv_child)

        var childRect = Rect()
        var childLocs = intArrayOf(0,0)
        var oldY = 0f

        sv_parent.setOnTouchListener(object : View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {

                Log.d("LOG", "parent")
                sv_child.parent.requestDisallowInterceptTouchEvent(false)

                return false
            }
        })

        sv_child.setOnTouchListener(object : View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {

                sv_child.getLocationInWindow(childLocs)
//                Log.d("LOG", "sv_child.locs: " + childLocs[0]+"/"+childLocs[1])
                if(childLocs[1] <= 96)
                    sv_child.parent.requestDisallowInterceptTouchEvent(false)
                else{
                    sv_parent.post(Runnable {
                        //disable scrollview cha
                        sv_child.parent.requestDisallowInterceptTouchEvent(true)
//                        when (event?.action) {
//                            MotionEvent.ACTION_DOWN ->
//                                oldY = event.y
//                            MotionEvent.ACTION_UP ->{
//                                var deltaY = event.y - oldY
//                                if(deltaY < 0)
//                                    sv_parent.scrollBy(0, deltaY.toInt())
//                            }
//                        }
                    })
                }

                return false
            }
        })
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        val gv_main = findViewById<View>(R.id.main_gv)

        sv_child.layoutParams.height = gv_main.height
    }

    override fun getAppContext(): Context {
        return app
    }
}
