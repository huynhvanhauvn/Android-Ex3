package com.sbro.androidex3

import android.app.Activity
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.khtn.androidcamp.DataCenter
import com.sbro.androidex3.Fragment.MyFavoriteFragment
import com.sbro.androidex3.Fragment.NowPlayingFragment
import com.sbro.androidex3.Fragment.TopRatingFragment

class MainActivity : AppCompatActivity() {

    var recycler : RecyclerView? = null
    var adapter : MovieAdapter? = null
    lateinit var btnList : ImageButton
    lateinit var btnGrid : ImageButton
    private lateinit var viewPager:ViewPager
    private lateinit var bottomNavigationView:BottomNavigationView
    private lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

          supportActionBar?.hide()
        toolbar=findViewById(R.id.main_toolbar)
//        recycler = findViewById(R.id.main_list)
//        btnList = findViewById(R.id.main_btn_list)
//        btnGrid = findViewById(R.id.main_btn_grid)
//
//        val data = Gson().fromJson<Data>(DataCenter.getMovieJsonString(),Data::class.java)
//
//        var layoutManager : LinearLayoutManager = LinearLayoutManager(this)
//        layoutManager.orientation = LinearLayoutManager.VERTICAL
//        recycler!!.layoutManager = layoutManager
//        adapter = MovieAdapter(data.results,this, false)
//        recycler!!.adapter = adapter
//
//        btnList.setOnClickListener(object : View.OnClickListener{
//            override fun onClick(v: View?) {
//                var layoutManager : LinearLayoutManager = LinearLayoutManager(applicationContext)
//                layoutManager.orientation = LinearLayoutManager.VERTICAL
//                recycler!!.layoutManager = layoutManager
//                adapter = MovieAdapter(data.results,applicationContext, false)
//                recycler!!.adapter = adapter
//            }})
//        btnGrid.setOnClickListener(object : View.OnClickListener{
//            override fun onClick(v: View?) {
//                var layoutManager : GridLayoutManager = GridLayoutManager(applicationContext,2)
//                recycler!!.layoutManager = layoutManager
//                adapter = MovieAdapter(data.results,applicationContext, true)
//                recycler!!.adapter = adapter
//            }})
        viewPager = findViewById(R.id.viewPager)
        val fragmentAdapter  =ViewPagerAdapter(supportFragmentManager)
        viewPager.adapter= fragmentAdapter
        viewPager?.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun onPageSelected(position: Int) {
                bottomNavigationView.menu.getItem(position).setChecked(true)
                toolbar.title=fragmentAdapter.getPageTitle(position)
            }

        })
        //
        toolbar.title=fragmentAdapter.getPageTitle(0)
        //

        bottomNavigationView =findViewById(R.id.navBottom)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId){
                R.id.item_nowPlaying ->{
                    viewPager.currentItem =0
                    replaceFragment(NowPlayingFragment())
                    return@setOnNavigationItemSelectedListener true


                }
                R.id.item_topRating ->{
                    viewPager.currentItem =1
                    replaceFragment(TopRatingFragment())
                    return@setOnNavigationItemSelectedListener true

                }
                else ->{
                    viewPager.currentItem =2
                    replaceFragment(MyFavoriteFragment())
                    return@setOnNavigationItemSelectedListener true

                }
            }

        }
    }
    private fun replaceFragment(fragment:Fragment){
        val fragmentTransaction =supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer,fragment)
        fragmentTransaction.commit()
    }

}
