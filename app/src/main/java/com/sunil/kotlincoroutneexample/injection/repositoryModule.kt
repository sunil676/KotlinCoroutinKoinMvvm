package com.sunil.kotlincoroutneexample.injection

import com.sunil.kotlincoroutneexample.api.RemoteRepository
import org.koin.dsl.module.applicationContext

val repositoryModule = applicationContext {
    factory { RemoteRepository(get()) }
}