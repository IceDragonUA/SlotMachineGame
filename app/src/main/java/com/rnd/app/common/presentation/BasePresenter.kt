package com.rnd.app.common.presentation

abstract class BasePresenter<V : MvpView>() : MvpPresenter<V> {

    protected var view: V? = null

    override fun onAttach(view: V) {
        this.view = view
    }

    override fun onDetach() {
        this.view = null
    }
}