package com.example.lab3.ui.movie.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.lab3.BuildConfig
import com.example.lab3.domain.movie.model.MovieResponse
import com.example.lab3.domain.movie.retrofit.MovieDbApi
import com.example.lab3.domain.movie.retrofit.MovieDbApiProvider
import com.example.lab3.domain.movie.room.AppDatabase
import com.example.lab3.ui.movie.adapters.MovieAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDataViewModel(application: Application) : AndroidViewModel(application) {

    private var omdbApi: MovieDbApi = MovieDbApiProvider.getOMDbApi()!!
    private var movieListMutableLiveData: MutableLiveData<MovieResponse> = MutableLiveData()
    private val database: AppDatabase = AppDatabase.getInstance(application)

    //BuildConfig.MOVIE_DB_API_KEY == 71c8940b
    fun searchMoviesByTitle(title: String) {
        omdbApi.getMoviesByTitle(BuildConfig.MOVIE_DB_API_KEY, "1", "movie", title)
            .enqueue(object :
                Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>?,
                    response: Response<MovieResponse>
                ) {
                    println("this is onResponse")
                    if (response.body() != null) {
                        val movies: MovieResponse = response.body()!!
                        saveMovieListInDatabase(movies)
                        movieListMutableLiveData.postValue(movies)
                    }
                }

                override fun onFailure(call: Call<MovieResponse>?, t: Throwable) {
                    println("this is onFailure")
                }
            })
    }

    private fun saveMovieListInDatabase(currentMovieList: MovieResponse) {
        println("this is saveMovieListInDatabase")
        CoroutineScope(Dispatchers.IO).launch {
            database.movieDao().insertMovies(currentMovieList.Search)
        }
    }

    fun getMovieListMutableLiveData(): MutableLiveData<MovieResponse> {
        println("this is getMovieListMutableLiveData")
        return movieListMutableLiveData;
    }
}