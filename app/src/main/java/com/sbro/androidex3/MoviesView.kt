package com.sbro.androidex3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MoviesView() : AppCompatActivity() {
    var recyclerView : RecyclerView? = null

    var moviesList : Array<Movie>? = null

    init {
        this.moviesList = arrayOf(Movie(popularity = 195.72,
                                        voteCount = 2056,
                                        video = false,
            posterPath = "/8WUVHemHFH2ZIP6NWkwlHWsyrEL.jpg",
            id = 338762,
            adult = false,
            backdropPath = "/ocUrMYbdjknu2TwzMHKT9PBBQRw.jpg",
            originalLanguage = "en",
            originalTitle = "bloodshot",
            genreIds = listOf(28, 878),
            title = "bloodshot",
            voteAverage = 7.1,
            overview = "After he and his wife are murdered, marine Ray Garrison is resurrected by a team of scientists. Enhanced with nanotechnology, he becomes a superhuman, biotech killing machine—\\'bloodshot\\'. As Ray first trains with fellow super-soldiers, he cannot recall anything from his former life. But when his memories flood back and he remembers the man that killed both him and his wife, he breaks out of the facility to get revenge, only to discover that there\\'s more to the conspiracy than he thought.",
            releaseDate = "2020-03-05"
        ))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_view)
        var view = this.recyclerView

        view = findViewById(R.id.id)

//        var adapter = MovieAdapter(moviesList, this, this)
//        view.adapter = adapter
//        var layout = LinearLayoutManager(this)
//        view.layoutManager = layout
    }
}
