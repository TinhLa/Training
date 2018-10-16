package com.training.tinhla.training.scrollscreen

import com.training.tinhla.training.base.model.json.ButtonModel
import com.training.tinhla.training.base.model.json.TemplateLineModel
import com.training.tinhla.training.base.mvp.BasePresenter
import com.training.tinhla.training.base.mvp.BaseView
import com.training.tinhla.training.scrollscreen.header_images.FragmentsViewPager

interface ScrollInterface {

    interface View : BaseView {
        fun setupBgHeaderViewPager(adapter: FragmentsViewPager)

        fun addHeaderLine(line: TemplateLineModel)

        fun addBodyLine(line: TemplateLineModel)

        fun addDrawLineInBody()

        fun addEmptyLineInBody()

        fun addTemplateButtons(templateButtons: ArrayList<ButtonModel>?)
    }

    interface Presenter : BasePresenter {

    }
}