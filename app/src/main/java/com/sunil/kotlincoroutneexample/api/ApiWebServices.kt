package com.sunil.kotlincoroutneexample.api

import com.sunil.kotlincoroutneexample.BuildConfig
import com.sunil.kotlincoroutneexample.data.MoviesResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiWebServices {

    @GET("movie/popular?api_key=${BuildConfig.API_KEY}")
    fun loadMovies(): Deferred<MoviesResponse>
}