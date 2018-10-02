package com.training.tinhla.training.splashscreen

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.InstanceIdResult
import com.training.tinhla.training.R
import com.training.tinhla.training.base.model.json.ButtonModel
import com.training.tinhla.training.base.model.json.TemplateLineModel
import com.training.tinhla.training.basemodel.BaseActivity
import com.training.tinhla.training.splashscreen.header_images.FragmentsViewPager
import com.training.tinhla.training.splashscreen.helper.CreateViewHelper
import com.training.tinhla.training.splashscreen.helper.SlidePanelInstaller
import kotlinx.android.synthetic.main.content_splash.*
import kotlinx.android.synthetic.main.layout_header_iframe.*
import kotlinx.android.synthetic.main.layout_panel_body.*
import javax.inject.Inject


class SplashActivity : BaseActivity(), SplashInterface.View {
    @Inject
    lateinit var presenter: SplashInterface.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        presenter.onCreate()

        // setup processes of views for scroll action
        SlidePanelInstaller(sliding_layout, gv_header_iframe, view_main_content, sv_main).installScrollProcess(vp_images)

        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener(this, object:OnSuccessListener<InstanceIdResult>{
            override fun onSuccess(result: InstanceIdResult?) {
                val token = result?.token
                Log.d("LOG", "recent firebase token: " + token)
            }
        })
    }

    override fun setupBgHeaderViewPager(adapter: FragmentsViewPager) {
        vp_images.adapter = adapter
    }

    override fun addHeaderLine(line: TemplateLineModel) {
        CreateViewHelper.addLine(this, gv_header_iframe, line)
    }

    override fun addBodyLine(line: TemplateLineModel) {
        CreateViewHelper.addLine(this, gv_panel, line)
    }

    override fun addDrawLineInBody() {
        CreateViewHelper.addDrawLine(gv_panel)
    }

    override fun addEmptyLineInBody() {
        CreateViewHelper.addEmptyLine(gv_panel)
    }

    override fun addTemplateButtons(templateButtons: ArrayList<ButtonModel>?) {
        if (templateButtons != null) {
            CreateViewHelper.addTemplateButtons(line_template_buttons, templateButtons)
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        // align SlidingUpPanel below icon of IFrameHeader
        positionSlidingUpPanelBelowIconHeader()
    }

    // position SlidingUpPanel below icon of IFrame Header
    private fun positionSlidingUpPanelBelowIconHeader() {
        if (gv_header_iframe.childCount > 0) {
            val topView = gv_header_iframe.getChildAt(0)

            // check if the first line of header is image (icon)
            if ((topView as ViewGroup).getChildAt(0) is ImageView) {
                val locs1 = intArrayOf(0,0)
                val locs2 = intArrayOf(0,0)

                btn_back.getLocationInWindow(locs1)
                topView.getLocationInWindow(locs2)

                val lp = sliding_layout.layoutParams as RelativeLayout.LayoutParams

                lp.topMargin = gv_header_iframe.getChildAt(1).top + gv_header_iframe.top - btn_back.bottom
                sliding_layout.layoutParams = lp
            }
        }
    }

    override fun getContext(): Context {
        return app
    }
}
