package com.example.lab3.ui.movie.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.lab3.domain.movie.model.Movie
import com.example.lab3.domain.movie.room.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailsDataViewModel(application: Application) : AndroidViewModel(application) {

    private var movieLiveData: MutableLiveData<Movie> = MutableLiveData()
    private val database: AppDatabase = AppDatabase.getInstance(application)

    fun getMovieMutableLiveData(): MutableLiveData<Movie> {

        return movieLiveData;
    }

    fun findMovieByTitle(title: String) {

        CoroutineScope(Dispatchers.IO).launch {
            movieLiveData.postValue(database.movieDao().getMovies(title)[0])
        }
    }
}