package com.example.lab3.domain.movie.retrofit

import com.example.lab3.domain.movie.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDbApi {

    @GET(".")
    fun getMoviesByTitle(
        @Query("apikey") apiKey: String,
        @Query("page") page: String,
        @Query("type") type: String,
        @Query("s") s: String
    ): Call<MovieResponse>
}