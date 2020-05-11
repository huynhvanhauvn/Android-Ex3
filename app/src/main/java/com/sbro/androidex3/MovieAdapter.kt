package com.sbro.androidex3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(moviesTitle : Array<String>,
                   moviesContent : Array<String>,
                   moviesImage : Array<Int>,
                   ct : Context) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    var moviesTitleList = listOf<String>()
    var moviesContentList = listOf<String>()
    var imageList = listOf<Int>()
    var context : Context? = null

    init {
        moviesTitleList = moviesTitle.toList()
        moviesContentList = moviesContent.toList()
        imageList = moviesImage.toList()
        context = ct
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
        return moviesTitleList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title?.text = moviesTitleList.get(position)
        holder.content?.text = moviesContentList.get(position)
        holder.image?.setImageResource(imageList[position])
        holder.bind(moviesTitleList.get(position))
    }
}