package com.sbro.androidex3

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sbro.androidex3.Fragment.MyFavoriteFragment
import com.sbro.androidex3.Fragment.NowPlayingFragment
import com.sbro.androidex3.Fragment.TopRatingFragment
import com.sbro.androidex3.Room.MovieDatabase

class ViewPagerAdapter (fragmentManager: FragmentManager, db : MovieDatabase):FragmentPagerAdapter(fragmentManager){
    var db = db

    override fun getItem(position: Int): Fragment {
         return when (position){
             0->{
                 NowPlayingFragment(db)
             }
             1->{
                 TopRatingFragment(db)
             }
             else->{
                 return MyFavoriteFragment()
             }
        }
    }

    override fun getCount(): Int {
        return  3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position){
            0 -> "Now Playing"
            1->"Top Rating"
            else ->{
                return "My Favorite"
            }
        }
    }
}