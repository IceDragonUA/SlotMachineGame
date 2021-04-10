package com.rnd.app.welcome

import com.rnd.app.common.presentation.MvpPresenter
import com.rnd.app.common.presentation.MvpView

interface WelcomeContract {

    interface View : MvpView {
        fun initView()
    }

    interface Presenter : MvpPresenter<View> {
        fun loadData()
    }
}