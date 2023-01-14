package com.example.lab3.ui.movie.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lab3.domain.movie.model.Movie

class SelectedMovieTitleViewModel : ViewModel() {

    private val movie_title = MutableLiveData<String>()

    val selectedMovie: LiveData<String> get() = movie_title

    fun selectMovieByTitle(selectedMovieTitle: String){
        movie_title.value = selectedMovieTitle
    }
}