package com.sbro.androidex3

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.khtn.androidcamp.DataCenter

class MainActivity : AppCompatActivity() {

    var recycler : RecyclerView? = null
    var adapter : MovieAdapter? = null
    lateinit var btnList : ImageButton
    lateinit var btnGrid : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        recycler = findViewById(R.id.main_list)
        btnList = findViewById(R.id.main_btn_list)
        btnGrid = findViewById(R.id.main_btn_grid)

        val data = Gson().fromJson<Data>(DataCenter.getMovieJsonString(),Data::class.java)

        var layoutManager : LinearLayoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recycler!!.layoutManager = layoutManager
        adapter = MovieAdapter(data.results,this, false)
        recycler!!.adapter = adapter

        btnList.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                var layoutManager : LinearLayoutManager = LinearLayoutManager(applicationContext)
                layoutManager.orientation = LinearLayoutManager.VERTICAL
                recycler!!.layoutManager = layoutManager
                adapter = MovieAdapter(data.results,applicationContext, false)
                recycler!!.adapter = adapter
            }})
        btnGrid.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                var layoutManager : GridLayoutManager = GridLayoutManager(applicationContext,2)
                recycler!!.layoutManager = layoutManager
                adapter = MovieAdapter(data.results,applicationContext, true)
                recycler!!.adapter = adapter
            }})
    }
}
