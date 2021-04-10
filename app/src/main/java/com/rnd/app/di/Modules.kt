package com.rnd.app.di

import com.rnd.app.home.homeModule
import com.rnd.app.splash.splashModule
import com.rnd.app.welcome.welcomeModule

val koinModules = listOf(
    splashModule,
    welcomeModule,
    homeModule
)