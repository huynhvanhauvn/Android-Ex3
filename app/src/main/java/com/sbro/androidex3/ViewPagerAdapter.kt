package com.sbro.androidex3

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sbro.androidex3.Fragment.MyFavoriteFragment
import com.sbro.androidex3.Fragment.NowPlayingFragment
import com.sbro.androidex3.Fragment.TopRatingFragment

class ViewPagerAdapter (fragmentManager: FragmentManager):FragmentPagerAdapter(fragmentManager){
    override fun getItem(position: Int): Fragment {
         return when (position){
             0->{
                 NowPlayingFragment(false)
             }
             1->{
                 TopRatingFragment(false)
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