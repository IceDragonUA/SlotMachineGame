package com.rnd.app.home

import com.rnd.app.common.presentation.MvpPresenter
import com.rnd.app.common.presentation.MvpView

interface HomeContract {

    interface View : MvpView {
        fun initView(currentBet: Int, currentCredit: Int, currentWin: Int)

        fun enableBetOne(isEnabled: Boolean)
        fun enableBetMax(isEnabled: Boolean)
        fun enableSpine(isEnabled: Boolean)
    }

    interface Presenter : MvpPresenter<View> {
        fun loadData()

        fun betOnePressed()
        fun betMaxPressed()
        fun spinPressed()

        fun isBetOneEnabled(): Boolean
        fun isBetMaxEnabled(): Boolean
        fun isSpinEnabled(): Boolean
    }
}