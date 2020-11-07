package com.example.movieappkotlin.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import java.util.*

class DetailsPagePagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm!!) {
    private val mFragmentList: MutableList<Fragment> = ArrayList()

    // This list of type string is for the title of the tab
    private val mFragmentTitleList: MutableList<String> = ArrayList()
    fun addFragment(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

    override fun getItem(i: Int): Fragment {
        return mFragmentList[i]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }
}
