package com.khoiron.footballapps.ui.main


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.khoiron.footballapps.R
import com.khoiron.footballapps.presenter.event.LastEventPresenter
import com.khoiron.footballapps.presenter.event.NextEventPresenter
import com.khoiron.footballapps.ui.event.LastEventFragment
import com.khoiron.footballapps.ui.event.NextEventFragment
import kotlinx.android.synthetic.main.fragment_match.*

/**
 * A simple [Fragment] subclass.
 *
 */
class MatchFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(NextEventFragment(), "Next")
        adapter.addFragment(LastEventFragment(), "Last")
        viewPager.adapter = adapter
    }
}
