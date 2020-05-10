package com.sbro.androidex3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.IntegerRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MoviesView : AppCompatActivity() {
    var recyclerView : RecyclerView? = null

    var moviesTitle = arrayOf<String>()
    var moviesContent = arrayOf<String>()
    var image = arrayOf<Int>(R.drawable.bloodshot, R.drawable.sonic_the_hedgehog, R.drawable.fantasy_island)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_view)
        moviesTitle = resources.getStringArray(R.array.movieTitleList)
        moviesContent = resources.getStringArray(R.array.movieContentList)
        var view = this.recyclerView

        view = findViewById(R.id.id)

        var adapter = MovieAdapter(moviesTitle, moviesContent, image, this)
        view.adapter = adapter
        var layout = LinearLayoutManager(this)
        view.layoutManager = layout
    }
}
