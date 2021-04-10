package com.rnd.app.splash

import androidx.navigation.NavDirections
import com.rnd.app.common.presentation.MvpPresenter
import com.rnd.app.common.presentation.MvpView

interface SplashContract {

    interface View : MvpView {
        fun initView()
        fun navigateTo(direction: NavDirections)
    }

    interface Presenter : MvpPresenter<View> {
        fun loadData()
    }
}