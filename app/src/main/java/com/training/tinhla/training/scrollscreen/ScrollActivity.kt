package com.training.tinhla.training.scrollscreen

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.InstanceIdResult

import com.training.tinhla.training.R
import com.training.tinhla.training.base.model.constant.ACTIVITY_ID
import com.training.tinhla.training.base.model.json.ButtonModel
import com.training.tinhla.training.base.model.json.TemplateLineModel
import com.training.tinhla.training.basemodel.BaseActivity
import com.training.tinhla.training.scrollscreen.header_images.FragmentsViewPager
import com.training.tinhla.training.scrollscreen.helper.CreateViewHelper
import com.training.tinhla.training.scrollscreen.helper.SlidePanelInstaller
import kotlinx.android.synthetic.main.activity_scroll.*
import kotlinx.android.synthetic.main.content_scroll.*
import kotlinx.android.synthetic.main.layout_header_iframe.*
import kotlinx.android.synthetic.main.layout_panel_body.*
import javax.inject.Inject

class ScrollActivity : BaseActivity(), ScrollInterface.View {
    @Inject
    lateinit var presenter: ScrollInterface.Presenter

    private var isMainGVAddViewTree = false
    private var isImagesVPAddViewTree = false
    private var isSlidingPanelAddViewTree = false

    override fun getActivityID(): String {
        return ACTIVITY_ID.SCROLL
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_scroll)

        val screenHeight = resources.displayMetrics.heightPixels
        val bgHeaderHeight = resources.getDimensionPixelSize(R.dimen.height_bg_header)
        sliding_layout.panelHeight = screenHeight - bgHeaderHeight

        presenter.onCreate()

        // setup processes of views for scroll action
        SlidePanelInstaller(sliding_layout, gv_header_iframe, view_main_content, sv_main).installScrollProcess(vp_images)

        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener(this, object: OnSuccessListener<InstanceIdResult> {
            override fun onSuccess(result: InstanceIdResult?) {
                val token = result?.token
                Log.d("LOG", "recent firebase token: " + token)
            }
        })
    }

    private fun setPanelHeightIfCan() {
        if (isMainGVAddViewTree && isImagesVPAddViewTree && isSlidingPanelAddViewTree) {
            val height = main_gv.height - vp_images.height
            sliding_layout.panelHeight = height
        }
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
