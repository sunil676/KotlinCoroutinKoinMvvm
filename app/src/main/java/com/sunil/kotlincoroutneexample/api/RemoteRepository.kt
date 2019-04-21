package com.sunil.kotlincoroutneexample.api

import com.sunil.kotlincoroutneexample.data.MoviesResponse

class RemoteRepository(private val apiWebServices: ApiWebServices) {

    suspend fun loadMovie(): MoviesResponse {
        return apiWebServices.loadMovies().await()
    }
}