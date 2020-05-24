package com.sbro.androidex3.Fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.khtn.androidcamp.DataCenter
import com.sbro.androidex3.*
import com.sbro.androidex3.Room.MovieDatabase

import kotlinx.android.synthetic.main.fragment_now_playing.*

class NowPlayingFragment(db : MovieDatabase) : Fragment() {

    lateinit var ACTIVITY : MainActivity
    lateinit var btnList : ImageButton
    lateinit var btnGrid : ImageButton
    var db : MovieDatabase = db

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ACTIVITY = context as MainActivity
    }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = Gson().fromJson<Data>(DataCenter.getNowPlayingMovieJson(), Data::class.java)
        btnList = view.findViewById<ImageButton>(R.id.now_btn_list)
        btnGrid = view.findViewById<ImageButton>(R.id.now_btn_grid)

        var layoutManager : LinearLayoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recycleView!!.layoutManager = layoutManager
        var adapter = context?.let { MovieAdapter(data.results, it, false, db, ACTIVITY) }
        recycleView!!.adapter = adapter

        btnList.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                var layoutManager : LinearLayoutManager = LinearLayoutManager(context)
                layoutManager.orientation = LinearLayoutManager.VERTICAL
                recycleView!!.layoutManager = layoutManager
                adapter = context?.let { MovieAdapter(data.results, it, false, db, ACTIVITY) }
                recycleView!!.adapter = adapter
            }})
        btnGrid.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                var layoutManager : GridLayoutManager = GridLayoutManager(context,2)
                recycleView!!.layoutManager = layoutManager
                adapter = context?.let { MovieAdapter(data.results, it, true, db, ACTIVITY) }
                recycleView!!.adapter = adapter
            }})
    }
}
