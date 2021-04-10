package com.rnd.app.home

import com.rnd.app.common.DEFAULT_BET_MAX
import com.rnd.app.common.DEFAULT_BET_ONE
import com.rnd.app.common.DEFAULT_CREDIT
import com.rnd.app.common.DEFAULT_WIN
import com.rnd.app.common.presentation.BasePresenter

class HomePresenter : BasePresenter<HomeContract.View>(), HomeContract.Presenter {

    private var currentBet = DEFAULT_BET_ONE
    private var currentCredit = DEFAULT_CREDIT
    private var currentWin = DEFAULT_WIN

    private var isGameStarted = false

    override fun loadData() {
        view?.initView(currentBet, currentCredit, currentWin)

        view?.enableBetOne(isBetOneEnabled())
        view?.enableBetMax(isBetMaxEnabled())
        view?.enableSpine(isSpinEnabled())
    }

    override fun betOnePressed() {

    }

    override fun betMaxPressed() {

    }

    override fun spinPressed() {

    }

    override fun isBetOneEnabled(): Boolean {
        return currentBet <= DEFAULT_BET_MAX && !isGameStarted
    }

    override fun isBetMaxEnabled(): Boolean {
        return !isGameStarted
    }

    override fun isSpinEnabled(): Boolean {
        return !isGameStarted
    }

}