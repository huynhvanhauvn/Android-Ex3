package com.sbro.androidex3

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.io.IOException
import java.io.InputStream
import java.util.ArrayList

class MovieAdapter(
    moviesList: ArrayList<Movie>,
    ct: Context,
    act: Activity) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    var moviesList = listOf<Movie>()
    var context : Context? = null
    var activity : Activity? = null

    init {
        if (moviesList != null) {
            this.moviesList = moviesList.toList()
        }
        this.context = ct
        this.activity = act
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title : TextView? = null
        var content : TextView? = null
        var image : ImageView? = null
        init {
            title = itemView.findViewById(R.id.movie_title)
            content = itemView.findViewById(R.id.movie_content)
            image = itemView.findViewById(R.id.imageView)
        }
        fun bind(title: String) {
            itemView.setOnClickListener(View.OnClickListener {
                Toast.makeText(itemView.context,
                                title,
                                Toast.LENGTH_LONG).show()
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
        holder.bind(curMovie.title)
        holder.title?.text = curMovie.title.replace("\\", "")
        holder.content?.text = curMovie.overview.replace("\\", "")
        try{
            NewThread("https://image.tmdb.org/t/p/w500" + curMovie.posterPath, holder.image, activity).start()
        }catch (e : IOException){
            println(e.message)
            holder.image?.setImageResource(R.drawable.ic_launcher_foreground)
        }

    }

    private class NewThread(Url : String, imgView : ImageView?, activity : Activity?) : Thread() {
        var activity : Activity? = null
        var url : String? = null
        var imgView : ImageView? = null

        init {
            this.url = Url
            this.imgView = imgView
            this.activity = activity
        }

        override fun run() {
            try {
                var bmp : Bitmap? = null
                var input : InputStream = java.net.URL(url.toString()).openStream()
                bmp = BitmapFactory.decodeStream(input)
                this.activity?.runOnUiThread(java.lang.Runnable {
                    this.imgView?.setImageBitmap(bmp)
                })
            }catch (e : Exception) {
                println(e.message)
            }
        }
    }
}