package com.sbro.androidex3.Fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.khtn.androidcamp.DataCenter
import com.sbro.androidex3.*
import com.sbro.androidex3.APIService.MovieService
import com.sbro.androidex3.Room.MovieDatabase

import kotlinx.android.synthetic.main.fragment_now_playing.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NowPlayingFragment(db : MovieDatabase) : Fragment() {

    lateinit var ACTIVITY : MainActivity
    lateinit var btnList : ImageButton
    lateinit var btnGrid : ImageButton
    lateinit var nestedScrollView: NestedScrollView
    var db : MovieDatabase = db
    lateinit var data:Data
    lateinit var movies : ArrayList<Movie>
    lateinit var adapter : MovieAdapter
    lateinit var mLayoutManager: RecyclerView.LayoutManager
    var curPage : Int = 1



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
        btnList = view.findViewById<ImageButton>(R.id.now_btn_list)
        btnGrid = view.findViewById<ImageButton>(R.id.now_btn_grid)
        nestedScrollView = view?.findViewById<NestedScrollView>(R.id.now_nest)

        var layoutManager : LinearLayoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recycleView!!.layoutManager = layoutManager
        movies = ArrayList()
        adapter = context?.let { MovieAdapter(movies, it, false, db, ACTIVITY) }!!
        recycleView!!.adapter = adapter
        getDataFromApi(1)

        btnList.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                var layoutManager : LinearLayoutManager = LinearLayoutManager(context)
                layoutManager.orientation = LinearLayoutManager.VERTICAL
                recycleView!!.layoutManager = layoutManager
                adapter = context?.let { MovieAdapter(movies, it, false, db, ACTIVITY) }!!
                recycleView!!.adapter = adapter
            }})
        btnGrid.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                var layoutManager : GridLayoutManager = GridLayoutManager(context,2)
                recycleView!!.layoutManager = layoutManager
                adapter = context?.let { MovieAdapter(movies, it, true, db, ACTIVITY) }!!
                recycleView!!.adapter = adapter
            }})
        nestedScrollView.setOnScrollChangeListener(object :
            NestedScrollView.OnScrollChangeListener {
            override fun onScrollChange(
                v: NestedScrollView?,
                scrollX: Int,
                scrollY: Int,
                oldScrollX: Int,
                oldScrollY: Int
            ) {
                if(!nestedScrollView.canScrollVertically(1)) {
                    getDataFromApi(curPage+1)
                }
            }
        })
    }
    private fun getDataFromApi(page: Int) {
        MovieService.getInstance().getApi().getNowPlayingMovie(page).enqueue(object : Callback<Data> {
            override fun onFailure(call: Call<Data>?, t: Throwable?) {
                Log.e("retrofit","return error")
            }

            override fun onResponse(call: Call<Data>?, response: Response<Data>?) {
                response.let {
                    data= it?.body()!!
                    Log.v("response now playing", data.toString())
                }
                movies.addAll(data.results)
                adapter.notifyDataSetChanged()
                curPage = page
            }

        })
    }
}
