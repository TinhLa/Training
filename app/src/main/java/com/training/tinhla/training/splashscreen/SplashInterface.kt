package com.training.tinhla.training.splashscreen

import android.content.Context
import com.training.tinhla.training.base.model.json.ButtonJSON
import com.training.tinhla.training.base.model.json.ColumnJSON
import com.training.tinhla.training.base.model.json.ParameterImageJSON
import com.training.tinhla.training.base.model.json.TemplateLineJSON
import com.training.tinhla.training.base.mvp.BasePresenter
import com.training.tinhla.training.base.mvp.BaseView

interface SplashInterface {

    interface View : BaseView{
        fun setupBgHeaderViewPager(adapter:FragmentsViewPager)

        fun addImageViewToHeaderIFrame(data:ColumnJSON)

        fun addTextViewToHeaderIFrame(data:ColumnJSON)

        fun addNormalLineToBody(normalLineJSON: ColumnJSON)

        fun addLineTwoColumnsInBodyLine(line: TemplateLineJSON)

        fun addDrawLineInBody()

        fun addEmptyLineInBody()

        fun createNewButtons(buttons: ArrayList<ButtonJSON>)
    }

    interface Presenter : BasePresenter{

    }
}

