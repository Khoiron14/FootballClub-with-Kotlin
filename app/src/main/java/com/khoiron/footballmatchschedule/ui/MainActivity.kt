package com.khoiron.footballmatchschedule.ui

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.khoiron.footballmatchschedule.R
import com.khoiron.footballmatchschedule.ui.LastMatch.LastMatchFragment
import com.khoiron.footballmatchschedule.ui.NextMatch.NextMatchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var tabLayout: TabLayout? = null
    private var viewPage: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPage = view_pager as ViewPager
        setupViewPager(viewPage!!)

        tabLayout = tab_layout as TabLayout
        tabLayout?.setupWithViewPager(viewPage)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(LastMatchFragment(), "Last Match")
        adapter.addFragment(NextMatchFragment(), "Next Match")
        viewPager.adapter = adapter
    }
}
