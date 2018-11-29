package com.khoiron.footballapps.ui.team

import com.khoiron.footballapps.data.model.team.Team

/**
 * Created by Khoiron14 on 30/11/18.
 */
interface TeamView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}