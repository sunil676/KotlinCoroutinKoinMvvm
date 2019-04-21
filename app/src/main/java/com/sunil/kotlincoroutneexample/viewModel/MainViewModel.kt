package com.sunil.kotlincoroutneexample.viewModel

import android.arch.lifecycle.MutableLiveData
import com.sunil.kotlincoroutneexample.api.ApiWebServices
import com.sunil.kotlincoroutneexample.base.BaseViewModel
import com.sunil.kotlincoroutneexample.data.MovieEntity
import com.sunil.kotlincoroutneexample.extentions.launchAsync

class MainViewModel(private val repository: ApiWebServices) : BaseViewModel() {

    val movieList = MutableLiveData<List<MovieEntity>>()

    fun getMovieList() {

        launchAsync {
            try {
                //The data is loading
                setLoading()

                //Request with a suspended repository funcion
                val moviesResponse = repository.loadMovies()

                movieList.value = moviesResponse.await().results

            } catch (t: Throwable) {
                //An error was throw
                setError(t)
                movieList.value = emptyList()
            } finally {
                //Isn't loading anymore
                setLoading(false)
            }
        }
    }

    fun start() {
        movieList.value = emptyList()
    }
}