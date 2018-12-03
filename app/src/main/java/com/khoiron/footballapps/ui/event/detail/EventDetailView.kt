package com.khoiron.footballapps.ui.event.detail

import com.khoiron.footballapps.data.model.event.EventDetail
import com.khoiron.footballapps.data.model.team.Team

/**
 * Created by Khoiron14 on 30/11/18.
 */
interface EventDetailView {
    fun showLoading()
    fun hideLoading()
    fun showEventDetail(data: List<EventDetail>)
    fun showHomeTeam(data: List<Team>)
    fun showAwayTeam(data: List<Team>)
}