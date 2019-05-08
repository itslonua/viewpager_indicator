package com.babylone.indicatorsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        view_pager.adapter = SectionsPagerAdapter(this, supportFragmentManager)

        //set ViewPager
        view_pager_indicator.setupWithViewPager(view_pager)

        //set indicator size
        view_pager_indicator.setupIndicatorSize(3)
    }
}
