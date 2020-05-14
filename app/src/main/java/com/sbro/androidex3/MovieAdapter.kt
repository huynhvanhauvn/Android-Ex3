package com.sbro.androidex3

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.io.IOException
import java.io.InputStream
import java.util.ArrayList

class MovieAdapter(
    moviesList: ArrayList<Movie>,
    ct: Context,
    act: Activity) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    var moviesList = ArrayList<Movie>()
    var context : Context
    var activity : Activity

    init {
        if (moviesList != null) {
            this.moviesList = moviesList
        }
        this.context = ct
        this.activity = act
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title : TextView
        var content : TextView
        var image : ImageView
        init {
            title = itemView.findViewById(R.id.movie_title)
            content = itemView.findViewById(R.id.movie_content)
            image = itemView.findViewById(R.id.imageView)
        }
        fun bind(movie:Movie) {
            itemView.setOnClickListener(View.OnClickListener {
                Toast.makeText(itemView.context,
                                movie.title,
                                Toast.LENGTH_LONG).show()
                val intent =Intent(itemView.context,DetailMovieActivity::class.java).putExtra("movie",movie)
                itemView.context.startActivity(intent)

            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater = LayoutInflater.from(context)
        var view = inflater.inflate(R.layout.single_movie, parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var curMovie = moviesList.get(position)
        Log.d("hvhau","https://image.tmdb.org/t/p/w500/"+curMovie.poster_path)
        holder.bind(curMovie)
        holder.title?.text = curMovie.title.replace("\\", "")
        holder.content?.text = curMovie.overview.replace("\\", "")
        Glide.with(context).load("https://image.tmdb.org/t/p/w500/" + curMovie.poster_path).into(holder.image)
    }
}