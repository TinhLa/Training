package com.training.tinhla.training.splashscreen

import com.training.tinhla.training.base.model.json.ButtonModel
import com.training.tinhla.training.base.model.json.ColumnModel
import com.training.tinhla.training.base.model.json.TemplateLineModel
import com.training.tinhla.training.base.mvp.BasePresenter
import com.training.tinhla.training.base.mvp.BaseView

interface SplashInterface {

    interface View : BaseView{
        fun setupBgHeaderViewPager(adapter:FragmentsViewPager)

        fun addImageViewToHeaderIFrame(data:ColumnModel)

        fun addTextViewToHeaderIFrame(data:ColumnModel)

        fun addBodyLine(line: TemplateLineModel)

        fun addDrawLineInBody()

        fun addEmptyLineInBody()

        fun createNewButtons(buttons: ArrayList<ButtonModel>)
    }

    interface Presenter : BasePresenter{

    }
}

