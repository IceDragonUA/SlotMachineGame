package com.rnd.app.common.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment<in V : MvpView, P : MvpPresenter<V>> : Fragment(), MvpView {

    protected abstract val presenter: P

    @Suppress("UNCHECKED_CAST")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onAttach(this as V)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDetach()
    }
}