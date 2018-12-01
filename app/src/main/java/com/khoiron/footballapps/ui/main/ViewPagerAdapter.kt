package com.khoiron.footballapps.ui.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by Khoiron14 on 01/12/18.
 */
class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

    private val fragmentList = ArrayList<Fragment>()
    private val fragmentTitleList = ArrayList<String>()

    override fun getItem(p0: Int): Fragment {
        return fragmentList[p0]
    }

    override fun getCount(): Int = fragmentList.size

    override fun getPageTitle(position: Int): CharSequence? {
        super.getPageTitle(position)
        return fragmentTitleList[position]
    }

    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        fragmentTitleList.add(title)
    }
}