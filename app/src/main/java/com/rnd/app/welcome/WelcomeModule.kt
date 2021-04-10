package com.rnd.app.welcome

import org.koin.dsl.module

val welcomeModule = module {
    factory<WelcomeContract.Presenter> { WelcomePresenter() }
}