package com.rnd.app.home

import androidx.navigation.NavDirections
import com.rnd.app.common.presentation.MvpPresenter
import com.rnd.app.common.presentation.MvpView

interface HomeContract {

    interface View : MvpView {
        fun initView()
        fun navigateTo(direction: NavDirections)
    }

    interface Presenter : MvpPresenter<View> {
        fun loadData()
    }
}