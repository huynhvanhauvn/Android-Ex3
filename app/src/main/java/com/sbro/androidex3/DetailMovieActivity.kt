package com.sbro.androidex3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class DetailMovieActivity : AppCompatActivity() {

    val MOVIE_DATA : String = "movie"
    lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        supportActionBar?.hide()

        movie = intent.getSerializableExtra(MOVIE_DATA) as Movie

    }
}
