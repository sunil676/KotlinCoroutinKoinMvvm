package com.sunil.kotlincoroutneexample.ui

import android.os.Bundle
import com.sunil.kotlincoroutneexample.R
import com.sunil.kotlincoroutneexample.base.BaseActivity
import com.sunil.kotlincoroutneexample.viewModel.MainViewModel
import org.koin.android.architecture.ext.viewModel

class MainActivity : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showMovieFragment()

    }

    private fun showMovieFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment())
            .commit()
    }
}