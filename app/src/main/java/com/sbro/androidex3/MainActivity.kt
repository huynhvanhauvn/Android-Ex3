package com.sbro.androidex3

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.khtn.androidcamp.DataCenter

class MainActivity : AppCompatActivity() {

    var recycler : RecyclerView? = null
    var adapter : MovieAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler = findViewById(R.id.main_list)

        val data = Gson().fromJson<Data>(DataCenter.getMovieJsonString(),Data::class.java)
        var layoutManager : LinearLayoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recycler!!.layoutManager = layoutManager
        adapter = MovieAdapter(data.results,this, Activity())
        recycler!!.adapter = adapter
    }
}
