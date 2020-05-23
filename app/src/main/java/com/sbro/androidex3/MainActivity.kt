package com.sbro.androidex3

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sbro.androidex3.Fragment.MyFavoriteFragment
import com.sbro.androidex3.Fragment.NowPlayingFragment
import com.sbro.androidex3.Fragment.TopRatingFragment

class MainActivity : AppCompatActivity() {

    var listFavorite : ArrayList<Movie> = ArrayList()
    var adapter : MovieAdapter? = null
    private lateinit var viewPager:ViewPager
    private lateinit var bottomNavigationView:BottomNavigationView
    var currentFragment : Fragment = NowPlayingFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        adapter = MovieAdapter(listFavorite,applicationContext, false, this)

        viewPager = findViewById(R.id.viewPager)
        val fragmentAdapter  =ViewPagerAdapter(supportFragmentManager)
        viewPager.adapter= fragmentAdapter
        var sharedPreferences = applicationContext.getSharedPreferences("myref", Context.MODE_PRIVATE)
        viewPager.currentItem=sharedPreferences.getInt("currentpage",0);
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
            }

        })
        //
        //

        bottomNavigationView =findViewById(R.id.navBottom)
        bottomNavigationView.menu.getItem(viewPager.currentItem).setChecked(true)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId){
                R.id.item_nowPlaying ->{
                    viewPager.currentItem =0
                    replaceFragment(NowPlayingFragment())
                    currentFragment = NowPlayingFragment()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.item_topRating ->{
                    viewPager.currentItem =1
                    replaceFragment(TopRatingFragment())
                    currentFragment = TopRatingFragment()
                    return@setOnNavigationItemSelectedListener true
                }
                else ->{
                    viewPager.currentItem =2
                    replaceFragment(MyFavoriteFragment())
                    currentFragment = MyFavoriteFragment()
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

    override fun onPause() {
        super.onPause()
        var sharedPreferences = applicationContext.getSharedPreferences("myref", Context.MODE_PRIVATE)
        var editor = sharedPreferences.edit()
        editor.putInt("currentpage",viewPager.currentItem)
        editor.apply()
    }
}
