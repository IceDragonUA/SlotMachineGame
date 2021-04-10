package com.rnd.app.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import com.rnd.app.R
import com.rnd.app.common.presentation.BaseActivity
import com.rnd.app.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject


class MainActivity : BaseActivity<MainContract.View, MainContract.Presenter>(), MainContract.View {

    private lateinit var binding: ActivityMainBinding

    override val presenter by inject<MainContract.Presenter>()

    private val navHostFragment: NavHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }

    private val navController: NavController by lazy {
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter.loadData()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    override fun initView() {

    }

    override fun navigateTo(direction: NavDirections) {

    }
}
