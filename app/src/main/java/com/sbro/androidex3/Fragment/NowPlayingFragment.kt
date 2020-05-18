package com.sbro.androidex3.Fragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.internal.`$Gson$Types`
import com.khtn.androidcamp.DataCenter
import com.sbro.androidex3.Data
import com.sbro.androidex3.Movie
import com.sbro.androidex3.MovieAdapter

import com.sbro.androidex3.R
import kotlinx.android.synthetic.main.fragment_now_playing.*

class NowPlayingFragment(isGrid : Boolean) : Fragment() {

    var isGrid : Boolean = isGrid

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_now_playing, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val data = Gson().fromJson<Data>(DataCenter.getNowPlayingMovieJson(), Data::class.java)
        recycleView.adapter = context?.let { MovieAdapter(data.results, it, this.isGrid) }
        recycleView.layoutManager = LinearLayoutManager(this.context)
    }
}
