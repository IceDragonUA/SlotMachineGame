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
        currentBet += DEFAULT_BET_ONE
        if (currentBet > DEFAULT_BET_MAX) {
            currentBet = DEFAULT_BET_ONE
        }
        if (currentCredit - currentBet < 0) {
            currentBet = DEFAULT_BET_ONE
        }
        view?.initView(currentBet, currentCredit, currentWin)
    }

    override fun betMaxPressed() {
        currentBet = DEFAULT_BET_MAX
        if (currentCredit - currentBet <= 0) {
            currentBet = currentCredit
        }
        view?.initView(currentBet, currentCredit, currentWin)

        if (currentBet == 0) {
            view?.enableBetOne(isBetOneEnabled())
            view?.enableBetMax(isBetMaxEnabled())
            view?.enableSpine(isSpinEnabled())
        }
    }

    override fun spinPressed() {
        currentCredit -= currentBet
        if (currentCredit - currentBet <= 0) {
            currentBet = currentCredit
        }
        view?.initView(currentBet, currentCredit, currentWin)
        view?.enableBetOne(isBetOneEnabled())
        view?.enableBetMax(isBetMaxEnabled())
        view?.enableSpine(isSpinEnabled())
    }

    override fun isBetOneEnabled(): Boolean {
        return currentBet <= DEFAULT_BET_MAX && !isGameStarted && currentCredit > 0
    }

    override fun isBetMaxEnabled(): Boolean {
        return !isGameStarted && currentCredit > 0
    }

    override fun isSpinEnabled(): Boolean {
        return !isGameStarted && currentCredit > 0
    }

}