package com.khoiron.footballapps.ui.team

import com.khoiron.footballapps.data.model.team.TeamDetail

/**
 * Created by Khoiron14 on 30/11/18.
 */
interface TeamDetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<TeamDetail>)
}