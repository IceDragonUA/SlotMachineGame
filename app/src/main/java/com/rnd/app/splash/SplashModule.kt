package com.rnd.app.splash

import org.koin.dsl.module

val splashModule = module {
    factory<SplashContract.Presenter> { SplashPresenter() }
}
