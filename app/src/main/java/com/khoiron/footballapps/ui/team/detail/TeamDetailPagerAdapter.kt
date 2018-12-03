package com.khoiron.footballapps.ui.team.detail

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.khoiron.footballapps.ui.team.detail.player.TeamPlayerFragment

/**
 * Created by Khoiron14 on 02/12/18.
 */
class TeamDetailPagerAdapter(manager: FragmentManager) :
    FragmentPagerAdapter(manager) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> TeamDetailOverviewFragment()
            else -> TeamPlayerFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Overview"
            else -> "Players"
        }
    }

    override fun getCount(): Int = 2

}