package com.rnd.app.main

import androidx.navigation.NavDirections
import com.rnd.app.common.presentation.MvpPresenter
import com.rnd.app.common.presentation.MvpView

interface MainContract {

    interface View : MvpView {
        fun initView()
        fun navigateTo(direction: NavDirections)
    }

    interface Presenter : MvpPresenter<View> {
        fun loadData()
    }
}