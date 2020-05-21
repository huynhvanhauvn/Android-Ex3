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
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.khtn.androidcamp.DataCenter
import com.sbro.androidex3.Data
import com.sbro.androidex3.MainActivity
import com.sbro.androidex3.MovieAdapter

import com.sbro.androidex3.R
import kotlinx.android.synthetic.main.fragment_now_playing.*


class MyFavoriteFragment : Fragment() {

    lateinit var ACTIVITY : MainActivity
    lateinit var btnGrid : ImageButton
    lateinit var btnList : ImageButton

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ACTIVITY = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnList = view?.findViewById<ImageButton>(R.id.favorite_btn_list)
        btnGrid = view?.findViewById<ImageButton>(R.id.favorite_btn_grid)
        val data = Gson().fromJson<Data>(DataCenter.getTopRateMovieJson(), Data::class.java)

        var layoutManager : LinearLayoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recycleView!!.layoutManager = layoutManager
        ACTIVITY.adapter?.isGrid = false
        recycleView!!.adapter = ACTIVITY.adapter

        btnList.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                var layoutManager : LinearLayoutManager = LinearLayoutManager(context)
                layoutManager.orientation = LinearLayoutManager.VERTICAL
                recycleView!!.layoutManager = layoutManager
                ACTIVITY.adapter?.isGrid = false
                recycleView!!.adapter = ACTIVITY.adapter
            }})
        btnGrid.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                var layoutManager : GridLayoutManager = GridLayoutManager(context,2)
                recycleView!!.layoutManager = layoutManager
                ACTIVITY.adapter?.isGrid = true
                recycleView!!.adapter = ACTIVITY.adapter
            }})
    }

}
