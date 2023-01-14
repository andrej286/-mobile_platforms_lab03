package com.example.lab3.domain.movie.retrofit

import com.example.lab3.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

/**
 * MovieDbApiProvider as an Interceptor.
 *
 * It is used to add variables to every request(like the apy key).
 */
class MovieDbApiProvider {

    companion object {
        private var omdbApi: MovieDbApi? = null

        fun getOMDbApi(): MovieDbApi? {

            if (omdbApi == null) {
                omdbApi = Retrofit.Builder()
                    .baseUrl("http://www.omdbapi.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(MovieDbApi::class.java)
            }

            return omdbApi
        }
    }

}