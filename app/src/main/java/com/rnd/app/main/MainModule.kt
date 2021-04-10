package com.rnd.app.main

import org.koin.dsl.module

val mainModule = module {
    factory<MainContract.Presenter> { MainPresenter() }
}