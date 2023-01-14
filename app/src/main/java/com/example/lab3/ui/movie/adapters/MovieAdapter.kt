package com.example.lab3.ui.movie.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lab3.R
import com.example.lab3.domain.movie.model.Movie

class MovieAdapter(
    private val context: Context,
    private var movies: MutableList<Movie>,
    private val onMovieClick: (position: Int) -> Unit
) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    class ViewHolder(view: View, private val onMovieClick: (position: Int) -> Unit) :
        RecyclerView.ViewHolder(view), View.OnClickListener {
        val poster: ImageView = view.findViewById(R.id.imageView)
        val title: TextView = view.findViewById(R.id.movieTitle)
        val year: TextView = view.findViewById(R.id.movieYear)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = absoluteAdapterPosition
            onMovieClick(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        return ViewHolder(view) { position ->
            onMovieClick(
                position
            )
        }

    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMovie = movies[position]

        Glide.with(context).load(currentMovie.Poster).into(holder.poster)
        holder.title.text = " Title: ${currentMovie.Title}"
        holder.year.text = " Year: ${currentMovie.Year}"
    }

    fun updateData(data: MutableList<Movie>) {
        this.movies = data
        this.notifyDataSetChanged()
    }
}