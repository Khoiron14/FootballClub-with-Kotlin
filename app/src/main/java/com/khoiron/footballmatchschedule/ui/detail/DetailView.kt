package com.khoiron.footballmatchschedule.ui.detail

import com.khoiron.footballmatchschedule.data.model.eventdetail.EventDetail
import com.khoiron.footballmatchschedule.data.model.team.Team

/**
 * Created by Khoiron14 on 21/11/18.
 */
interface DetailView {
    fun showLoading()
    fun hideLoading()
    fun showEventDetailList(data: List<EventDetail>)
    fun showHomeTeam(data: List<Team>)
    fun showAwayTeam(data: List<Team>)
}