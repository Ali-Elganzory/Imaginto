package com.ganzory.imaginto_test

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/****   Made by Ali Elganzory 29/7/2019    ****/

class SectionsStatePagerAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm) {

    var mFragmentList = mutableListOf<Fragment>()

    fun addFragment(fragment: Fragment){
        mFragmentList.add(fragment)
    }

    override fun getItem(p0: Int): Fragment {
        return mFragmentList[p0]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

}