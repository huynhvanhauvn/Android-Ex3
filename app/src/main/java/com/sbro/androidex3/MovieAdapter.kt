package com.sbro.androidex3

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.widget.Toast

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.bumptech.glide.Glide
import com.sbro.androidex3.Room.MovieDatabase
import kotlinx.android.synthetic.main.item_grid_movie.view.*
import java.util.ArrayList

class MovieAdapter(
    moviesList: ArrayList<Movie>,
    context: Context,
    isGrid: Boolean,
    db: MovieDatabase,
    val activity: MainActivity
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    var moviesList = ArrayList<Movie>()
    var context : Context
    var isGrid : Boolean = false
    var db : MovieDatabase

    init {
        if (moviesList != null) {
            this.moviesList = moviesList
        }
        this.context = context
        this.isGrid = isGrid
        this.db = db
    }

    class ViewHolder(itemView: View, db: MovieDatabase) : RecyclerView.ViewHolder(itemView) {
        var title : TextView
        var content : TextView
        var image : ImageView
        var db : MovieDatabase

        init {
            title = itemView.findViewById(R.id.movie_title)
            content = itemView.findViewById(R.id.movie_content)
            image = itemView.findViewById(R.id.imageView)
            this.db = db
        }
        fun bind(movie: Movie, activity: MainActivity, context: Context) {

            itemView.setOnClickListener(View.OnClickListener {
                Toast.makeText(itemView.context,
                                movie.title,
                                Toast.LENGTH_LONG).show()
                val intent =Intent(itemView.context,DetailMovieActivity::class.java).putExtra("movie",movie)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                itemView.context.startActivity(intent)
            })
            itemView.favoriteButton.setOnClickListener {
                if(db.movieDAO().getMovieByName(movie.title).isEmpty()){
                    db.movieDAO().insertMovie(movie)
                }
                if(!activity.listFavorite.contains(movie)){
                    AlertDialog.Builder(context)
                        .setTitle("Favorite")
                        .setMessage("Do you want to add this movie to Favorite")
                        .setPositiveButton("OK") { dialog, _ ->
                            activity.listFavorite.add(movie)
                            activity.adapter?.notifyDataSetChanged()
                            Toast.makeText(context, "Successful", Toast.LENGTH_SHORT).show()
                            dialog.dismiss()
                        }
                        .setNegativeButton("Cancel"){ dialog, _ ->
                            dialog.dismiss()
                        }.create().show()
                }
                else{
                    Toast.makeText(context,"Existed!",Toast.LENGTH_LONG).show()
                }

            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater = LayoutInflater.from(context)
        var view = inflater.inflate(R.layout.item_list_movie, parent,false)
        if(isGrid) {
            view = inflater.inflate(R.layout.item_grid_movie, parent,false)
        }
        return ViewHolder(view, db)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var curMovie = moviesList.get(position)
        Log.d("hvhau","https://image.tmdb.org/t/p/w500/"+curMovie.poster_path)
        holder.bind(curMovie, activity, context)
        holder.title?.text = curMovie.title.replace("\\", "")
        holder.content?.text = curMovie.overview.replace("\\", "")
        Glide.with(context).load("https://image.tmdb.org/t/p/w500/" + curMovie.poster_path).into(holder.image)
    }
}