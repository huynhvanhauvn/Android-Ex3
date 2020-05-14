package com.sbro.androidex3

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.material.appbar.CollapsingToolbarLayout

class DetailMovieActivity : AppCompatActivity() {
    lateinit var imgMovie: ImageView
    lateinit var imgRating: ImageView
    var description: TextView? = null
    var title: TextView? = null
    lateinit var rating: RatingBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar.setNavigationOnClickListener(View.OnClickListener {
            finish()
        })
        var collapsing_toolbar = findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar)
        //
        var movie = intent.getSerializableExtra("movie") as Movie

        title = findViewById(R.id.detailMovie_tv_titleMovie)
        title?.text = movie.title
        imgMovie = findViewById<ImageView>(R.id.movieDetail_imageMovie)
        Glide.with(this).load("https://image.tmdb.org/t/p/w500/" + movie.poster_path).into(imgMovie)
        imgRating = findViewById(R.id.detailMovie_imgPoster)
        Glide.with(this).load("https://image.tmdb.org/t/p/w500/" + movie.poster_path)
            .into(imgRating)
        description = findViewById(R.id.detailMovie_tv_description)
        description?.text = movie.overview.replace("\\", "")
        Log.v("tag", movie.overview)
        rating = findViewById(R.id.movieDetail_ratingBar)
        rating.rating = movie.vote_average.toFloat()


    }
}
