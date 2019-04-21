package com.sunil.kotlincoroutneexample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sunil.kotlincoroutneexample.R
import com.sunil.kotlincoroutneexample.base.BaseFragment
import com.sunil.kotlincoroutneexample.extentions.gone
import com.sunil.kotlincoroutneexample.extentions.observe
import com.sunil.kotlincoroutneexample.extentions.visible
import com.sunil.kotlincoroutneexample.viewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_movie.*
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.yesButton
import org.koin.android.architecture.ext.viewModel

class MainFragment : BaseFragment() {

    val viewModel: MainViewModel by viewModel()

    private var adapter: MovieAdapter = MovieAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.start()
        setupRecyclerView()
        movieApiCall()
        setupObservers()
    }

    private fun movieApiCall(){
        viewModel.getMovieList()
    }

    private fun setupRecyclerView() {
        recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        //Current call
        observe(viewModel.movieList, {
            it?.let {
                adapter.dataList = it
            }
            showNoDataFound(adapter.dataList.isEmpty())
        })

        //ProgressBar
        observe(viewModel.isDataLoading, {
            if (it == true) {
                viewProgressBar.visible()
            } else {
                viewProgressBar.gone()
            }
        })

        observe(viewModel.exception, {
            showErrorMessage(it?.message)
        })
    }

    private fun showNoDataFound(show: Boolean) {
        if (show) {
            viewError.visible()
            textViewError.text = getString(R.string.no_movie_found)
        } else {
            viewError.gone()
        }
    }

    private fun showErrorMessage(message: String?) {
        message?.let {
            alert(message, getString(R.string.error)) {
                yesButton { }
            }.show()
            viewModel.exception.value = null
        }
    }

}