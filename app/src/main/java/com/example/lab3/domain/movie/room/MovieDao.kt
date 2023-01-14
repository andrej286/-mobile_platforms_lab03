package com.example.lab3.domain.movie.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lab3.domain.movie.model.Movie

@Dao
abstract class MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMovies(data: MutableList<Movie>)

    @Query("SELECT * FROM Movie WHERE title = :title")
    abstract fun getMovies(title: String): List<Movie>
}