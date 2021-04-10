package com.rnd.app.common.presentation

interface MvpPresenter<in V : MvpView> {
    fun onAttach(view: V)
    fun onDetach()
}