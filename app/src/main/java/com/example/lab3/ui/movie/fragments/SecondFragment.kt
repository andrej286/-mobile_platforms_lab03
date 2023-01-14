package com.example.lab3.ui.movie.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.lab3.R
import com.example.lab3.domain.movie.model.Movie
import com.example.lab3.ui.movie.viewmodels.MovieDetailsDataViewModel
import com.example.lab3.ui.movie.viewmodels.SelectedMovieTitleViewModel

class SecondFragment : Fragment() {

    private lateinit var movieDetailsViewModel: MovieDetailsDataViewModel
    private val selectedMovieViewModel: SelectedMovieTitleViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_second, container, false)

        movieDetailsViewModel = ViewModelProvider(this).get(MovieDetailsDataViewModel::class.java)

        movieDetailsViewModel.getMovieMutableLiveData().observe(
            viewLifecycleOwner
        ) { it -> displayData(it!!, view) }

        selectedMovieViewModel.selectedMovie.observe(viewLifecycleOwner) {
            val title: String = it.toString()
            movieDetailsViewModel.findMovieByTitle(title)
        }

        return view
    }

    private fun displayData(movie: Movie, view: View) {

        val moviePoster = view.findViewById<ImageView>(R.id.moviePoster)
        val movieTitle = view.findViewById<TextView>(R.id.movieTitleDetails)
        val movieYear = view.findViewById<TextView>(R.id.movieYearDetails)

        movieTitle.text = movie.Title
        movieYear.text = movie.Year

        Glide.with(view.context).load(movie.Poster).into(moviePoster)
    }
}