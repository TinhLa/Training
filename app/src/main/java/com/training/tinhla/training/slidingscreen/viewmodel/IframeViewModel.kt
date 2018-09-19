package com.training.tinhla.training.slidingscreen.viewmodel

import android.databinding.BaseObservable
import android.os.Build
import android.support.annotation.RequiresApi
import com.training.tinhla.training.commonmodel.Template
import com.training.tinhla.training.databinding.ActivitySlidingBinding

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class IframeViewModel(activitySlidingBinding: ActivitySlidingBinding,template: Template) : BaseObservable() {
    init {
        activitySlidingBinding.frameHeader.createItem(template.templateBody.iframeProperty.templateLines)
        activitySlidingBinding.frameBody.createView(template.templateBody.templateLines)
        activitySlidingBinding.frameButton.onCreateView(template.templateButton)
    }

}