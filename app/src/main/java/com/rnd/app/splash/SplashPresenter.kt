package com.rnd.app.splash

import com.rnd.app.common.DEFAULT_MAX_PROGRESS
import com.rnd.app.common.DEFAULT_PROGRESS
import com.rnd.app.common.presentation.BasePresenter
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit

class SplashPresenter : BasePresenter<SplashContract.View>(), SplashContract.Presenter {

    private var progress: Int = 0
    private var disposable: Disposable? = null

    override fun loadData() {
        startLoading()
    }

    private fun startTimer() {
        Single.timer(DEFAULT_PROGRESS, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Long?> {
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onSuccess(aLong: Long) {
                    progress += 1

                    if (progress < 100) {
                        startLoading()
                    } else {
                        stopLoading()
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
    }

    private fun stopLoading() {
        stopTimer()
        view?.navigateTo(SplashFragmentDirections.actionSplashFragmentToWelcomeFragment())
    }

    private fun startLoading() {
        startTimer()
        view?.initView(progress.toFloat() / DEFAULT_MAX_PROGRESS)
    }


}