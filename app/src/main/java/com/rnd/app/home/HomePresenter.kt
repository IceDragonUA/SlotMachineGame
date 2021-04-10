package com.rnd.app.home

import com.rnd.app.R
import com.rnd.app.common.*
import com.rnd.app.common.presentation.BasePresenter
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class HomePresenter : BasePresenter<HomeContract.View>(), HomeContract.Presenter {

    private var progress: Int = 0
    private var disposable: Disposable? = null

    private var itemOne: Int = 0
    private var itemTwo: Int = 0
    private var itemThree: Int = 0

    private var lastBet = 0

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
        lastBet = currentBet
        currentCredit -= currentBet
        if (currentCredit - currentBet <= 0) {
            currentBet = currentCredit
        }
        view?.initView(currentBet, currentCredit, currentWin)
        view?.enableBetOne(isBetOneEnabled())
        view?.enableBetMax(isBetMaxEnabled())
        view?.enableSpine(isSpinEnabled())

        startGame()
    }

    private fun startGame() {
        isGameStarted = true

        Single.timer(DEFAULT_SPIN_TIME, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Long?> {
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onSuccess(aLong: Long) {
                    progress += 1

                    if (progress < DEFAULT_SPIN_COUNT) {
                        itemOne = Random.nextInt(0, 6)
                        itemTwo = Random.nextInt(0, 6)
                        itemThree = Random.nextInt(0, 6)

                        view?.initGame(itemOne, itemTwo, itemThree)

                        startGame()
                    } else {

                        stopTimer()
                    }
                }

                override fun onError(throwable: Throwable) {
                    Timber.e(throwable)
                }
            })
    }

    private fun stopTimer() {
        if (disposable != null) {
            disposable?.dispose()
        }
        isGameStarted = false
        progress = 0

        if (itemOne == itemTwo && itemOne == itemThree) {
            currentWin += (lastBet * initCoef(itemOne))
            view?.initWin(currentWin)
        }
    }

    private fun initCoef(itemOne: Int) : Int {
        return when (itemOne) {
            0 -> ITEM_1_C
            1 -> ITEM_2_C
            2 -> ITEM_3_C
            3 -> ITEM_4_C
            4 -> ITEM_5_C
            5 -> ITEM_6_C
            else -> ITEM_7_C
        }
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