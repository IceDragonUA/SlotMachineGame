package com.rnd.app.di

import com.rnd.app.home.homeModule
import com.rnd.app.main.mainModule
import com.rnd.app.splash.splashModule
import com.rnd.app.welcome.welcomeModule

val koinModules = listOf(
    mainModule,
    splashModule,
    welcomeModule,
    homeModule
)