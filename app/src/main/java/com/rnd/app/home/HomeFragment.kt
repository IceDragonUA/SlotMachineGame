package com.rnd.app.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rnd.app.R
import com.rnd.app.common.DEFAULT_MAX_PROGRESS
import com.rnd.app.common.presentation.BaseFragment
import com.rnd.app.databinding.FragmentHomeBinding
import org.koin.android.ext.android.inject
import java.lang.StringBuilder

class HomeFragment : BaseFragment<HomeContract.View, HomeContract.Presenter>(),
    HomeContract.View {

    private var binding: FragmentHomeBinding? = null
    private val rootView get() = binding!!.root

    override val presenter by inject<HomeContract.Presenter>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.loadData()

        binding?.table?.setOnClickListener {

        }
        binding?.betOne?.setOnClickListener {
            if (presenter.isBetOneEnabled()) {
                presenter.betOnePressed()
            }
        }
        binding?.betMax?.setOnClickListener {
            if (presenter.isBetMaxEnabled()) {
                presenter.betMaxPressed()
            }
        }
        binding?.spin?.setOnClickListener {
            if (presenter.isSpinEnabled()) {
                presenter.spinPressed()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun initView(currentBet: Int, currentCredit: Int, currentWin: Int) {
        binding?.bet?.text = normalizePrice(currentBet)
        binding?.credit?.text = normalizePrice(currentCredit)
        binding?.win?.text = normalizePrice(currentWin)
    }

    override fun initGame(itemOne: Int, itemTwo: Int, itemThree: Int) {
        binding?.item1?.setImageResource(initDrawable(itemOne))
        binding?.item2?.setImageResource(initDrawable(itemTwo))
        binding?.item3?.setImageResource(initDrawable(itemThree))
    }

    override fun initWin(win: Int) {
        binding?.win?.text = normalizePrice(win)
    }

    private fun initDrawable(itemOne: Int) =
        when (itemOne) {
            0 -> R.drawable.item0
            1 -> R.drawable.item1
            2 -> R.drawable.item2
            3 -> R.drawable.item3
            4 -> R.drawable.item4
            5 -> R.drawable.item5
            else -> R.drawable.item6
        }

    private fun normalizePrice(price: Int) =
        StringBuilder().apply {
            this.append(price)
            this.append(context?.resources?.getString(R.string.money_indicator))
        }.toString()

    override fun enableBetOne(isEnabled: Boolean) {
        binding?.betOne?.isEnabled = isEnabled
    }

    override fun enableBetMax(isEnabled: Boolean) {
        binding?.betMax?.isEnabled = isEnabled
    }

    override fun enableSpine(isEnabled: Boolean) {
        binding?.spin?.isEnabled = isEnabled
    }
}