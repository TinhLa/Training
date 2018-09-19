package com.training.tinhla.training.slidingscreen

import com.training.tinhla.training.commonmodel.Template
import com.training.tinhla.training.slidingscreen.model.Post

interface SlidingInterface {
    interface presenter{
        fun getListPost()
        fun getJsonTemplate()
    }
    interface view{
        fun success(posts : List<Post>)
        fun error(mes: String)
        fun onCreateFrameHeader(template: Template)
    }
}