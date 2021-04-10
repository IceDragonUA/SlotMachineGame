package com.rnd.app.home

import org.koin.dsl.module

val homeModule = module {
    factory<HomeContract.Presenter> { HomePresenter() }
}