package com.sbro.androidex3.Fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.internal.`$Gson$Types`
import com.khtn.androidcamp.DataCenter
import com.sbro.androidex3.*

import kotlinx.android.synthetic.main.fragment_now_playing.*

class TopRatingFragment() : Fragment() {

    lateinit var ACTIVITY : MainActivity
    lateinit var btnList : ImageButton
    lateinit var btnGrid : ImageButton

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
        return inflater.inflate(R.layout.fragment_top_rating, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = Gson().fromJson<Data>(DataCenter.getTopRateMovieJson(), Data::class.java)
        btnList = view?.findViewById<ImageButton>(R.id.top_btn_list)
        btnGrid = view?.findViewById<ImageButton>(R.id.top_btn_grid)

        var layoutManager : LinearLayoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recycleView!!.layoutManager = layoutManager
        var adapter = context?.let { MovieAdapter(data.results, it, false) }
        recycleView!!.adapter = adapter

        btnList.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                var layoutManager : LinearLayoutManager = LinearLayoutManager(context)
                layoutManager.orientation = LinearLayoutManager.VERTICAL
                recycleView!!.layoutManager = layoutManager
                adapter = context?.let { MovieAdapter(data.results, it, false) }
                recycleView!!.adapter = adapter
            }})
        btnGrid.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                var layoutManager : GridLayoutManager = GridLayoutManager(context,2)
                recycleView!!.layoutManager = layoutManager
                adapter = context?.let { MovieAdapter(data.results, it, true) }
                recycleView!!.adapter = adapter
            }})
    }
}
