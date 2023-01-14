package com.example.lab3.ui.movie.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab3.R
import com.example.lab3.domain.movie.model.Movie
import com.example.lab3.domain.movie.model.MovieResponse
import com.example.lab3.ui.movie.adapters.MovieAdapter
import com.example.lab3.ui.movie.viewmodels.MovieDataViewModel
import com.example.lab3.ui.movie.viewmodels.SelectedMovieTitleViewModel


class FirstFragment : Fragment() {
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var movieRecyclerView: RecyclerView
    private lateinit var movieDataViewModel: MovieDataViewModel
    private lateinit var movieList: MutableList<Movie>
    private val selectedMovieViewModel: SelectedMovieTitleViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieDataViewModel = ViewModelProvider(this).get(MovieDataViewModel::class.java)

        movieDataViewModel.getMovieListMutableLiveData().observe(viewLifecycleOwner,
            { it -> displayData(it!!) })

        movieRecyclerView = view.findViewById(R.id.list)

        movieRecyclerView.layoutManager = LinearLayoutManager(activity)

        movieAdapter = MovieAdapter(view.context, mutableListOf<Movie>()) { position ->
            onMovieClick(
                position
            )
        }

        movieRecyclerView.adapter = movieAdapter

        (movieRecyclerView.getLayoutManager() as LinearLayoutManager).stackFromEnd = true

        val searchBar: EditText = view.findViewById(R.id.editQuery)
        val searchButton: Button = view.findViewById(R.id.button)

        searchButton.setOnClickListener {
            val title: String = searchBar.text.toString()
            movieDataViewModel.searchMoviesByTitle(title)
            movieRecyclerView.scrollToPosition(0)
        }

        searchBar.setOnEditorActionListener{ _, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_ACTION_DONE){
                val title: String = searchBar.text.toString()
                movieDataViewModel.searchMoviesByTitle(title)
                movieRecyclerView.scrollToPosition(0)
                true
            }
            else {
                false
            }
        }
    }

    private fun displayData(data: MovieResponse) {

        movieList = data.Search

        movieAdapter.updateData(movieList)

        movieRecyclerView.scrollToPosition(0)
    }

    private fun onMovieClick(position: Int) {

        selectedMovieViewModel.selectMovieByTitle(movieList[position].Title);

        val fragment: Fragment = SecondFragment()
        val fragmentManager = activity!!.supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container_view, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}