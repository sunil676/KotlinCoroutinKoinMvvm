package com.sunil.kotlincoroutneexample.injection

import com.sunil.kotlincoroutneexample.viewModel.MainViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext

val viewModelModule = applicationContext {
    viewModel { MainViewModel(get()) }
}