package com.ganzory.imaginto_test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
//import com.Imaginto.TRIAL.UnityPlayerFragment
import kotlinx.android.synthetic.main.activity_insides.*

/****   Made by Ali Elganzory 29/7/2019    ****/

class Insides : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insides)

        val adapter = SectionsStatePagerAdapter(supportFragmentManager)
        adapter.addFragment(Market())
        adapter.addFragment(ARFragment())
        adapter.addFragment(Dashboard())

        viewPager.adapter = adapter

        insidesNavigation.setOnNavigationItemSelectedListener {

                when (it.itemId) {
                    R.id.market_btn -> {setViewPagerItem(0);  true}
                    R.id.ar_btn     -> {setViewPagerItem(1);  true}
                    R.id.promos_btn -> {setViewPagerItem(2);  true}
                    else            -> {false}
                }
        }

    }

    private fun setViewPagerItem(p0: Int){
        viewPager.currentItem = p0
    }

}
