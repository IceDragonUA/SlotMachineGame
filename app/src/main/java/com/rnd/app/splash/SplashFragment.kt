package com.rnd.app.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.rnd.app.R
import com.rnd.app.common.DEFAULT_MAX_PROGRESS
import com.rnd.app.common.presentation.BaseFragment
import com.rnd.app.databinding.FragmentSplashBinding
import org.koin.android.ext.android.inject
import java.lang.StringBuilder

class SplashFragment : BaseFragment<SplashContract.View, SplashContract.Presenter>(),
    SplashContract.View {

    private var binding: FragmentSplashBinding? = null
    private val rootView get() = binding!!.root

    override val presenter by inject<SplashContract.Presenter>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
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

    override fun initView(progress: Float) {
        binding?.text?.text = normalizeProgress(progress)
        binding?.progress?.setPrimaryProgress(endValue = progress)
    }

    private fun normalizeProgress(progress: Float) =
        StringBuilder().apply {
            this.append((progress * DEFAULT_MAX_PROGRESS).toInt())
            this.append(context?.resources?.getString(R.string.progress_indicator))
        }.toString()

    override fun navigateTo(direction: NavDirections) {
        view?.findNavController()?.navigate(direction)
    }
}