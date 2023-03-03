package com.amg.openlist.data.remote

import com.amg.openlist.data.remote.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {

    @GET("movie/{category}")
    suspend fun getMoviesByCategory(
        @Path("category") category: String
    ): Response<List<MovieResponse>>

}