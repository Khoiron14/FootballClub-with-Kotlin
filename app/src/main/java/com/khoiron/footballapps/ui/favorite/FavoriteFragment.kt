package com.khoiron.footballapps.ui.favorite


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.khoiron.footballapps.R
import com.khoiron.footballapps.ui.favorite.event.FavoriteEventFragment
import com.khoiron.footballapps.ui.favorite.team.FavoriteTeamFragment
import com.khoiron.footballapps.ui.main.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_favorite.*

/**
 * A simple [Fragment] subclass.
 *
 */
class FavoriteFragment : Fragment() {

    private var tabLayout: TabLayout? = null
    private var viewPage: ViewPager? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewPage = view_pager as ViewPager
        setupViewPager(viewPage!!)

        tabLayout = tab_layout as TabLayout
        tabLayout?.setupWithViewPager(viewPage)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(FavoriteEventFragment(), "Matches")
        adapter.addFragment(FavoriteTeamFragment(), "Teams")
        viewPager.adapter = adapter
    }
}
