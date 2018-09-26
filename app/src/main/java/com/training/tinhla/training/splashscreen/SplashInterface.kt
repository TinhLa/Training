package com.training.tinhla.training.splashscreen

import com.training.tinhla.training.base.model.json.ButtonModel
import com.training.tinhla.training.base.model.json.ColumnModel
import com.training.tinhla.training.base.model.json.TemplateLineModel
import com.training.tinhla.training.base.mvp.BasePresenter
import com.training.tinhla.training.base.mvp.BaseView

interface SplashInterface {

    interface View : BaseView{
        fun setupBgHeaderViewPager(adapter:FragmentsViewPager)

        fun addHeaderLine(line: TemplateLineModel)

        fun addBodyLine(line: TemplateLineModel)

        fun addDrawLineInBody()

        fun addEmptyLineInBody()

        fun addTemplateButtons(templateButtons: ArrayList<ButtonModel>?)
    }

    interface Presenter : BasePresenter{

    }
}

