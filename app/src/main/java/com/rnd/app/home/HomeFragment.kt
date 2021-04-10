package com.rnd.app.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.rnd.app.common.presentation.BaseFragment
import com.rnd.app.databinding.FragmentHomeBinding
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment<HomeContract.View, HomeContract.Presenter>(),
    HomeContract.View {

    private var binding: FragmentHomeBinding? = null
    private val rootView get() = binding!!.root

    override val presenter by inject<HomeContract.Presenter>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.loadData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun initView() {

    }

    override fun navigateTo(direction: NavDirections) {
        view?.findNavController()?.navigate(direction)
    }
}