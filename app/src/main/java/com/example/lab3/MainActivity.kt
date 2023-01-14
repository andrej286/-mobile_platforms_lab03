package com.example.lab3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.example.lab3.ui.movie.fragments.FirstFragment
import com.example.lab3.ui.movie.viewmodels.SelectedMovieTitleViewModel

class MainActivity : AppCompatActivity() {

    private val selectedMovieViewModel: SelectedMovieTitleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val fragmentManager: FragmentManager = supportFragmentManager
            fragmentManager.beginTransaction()
                .add(R.id.fragment_container_view, FirstFragment())
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit()
        }
    }
}