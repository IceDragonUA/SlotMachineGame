package com.rnd.app.common.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<in V : MvpView, P : MvpPresenter<V>> : AppCompatActivity(), MvpView {

    protected abstract val presenter: P

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onAttach(this as V)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}