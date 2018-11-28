package com.khoiron.footballmatchschedule.presenter

import com.google.gson.Gson
import com.khoiron.footballmatchschedule.data.api.ApiRepository
import com.khoiron.footballmatchschedule.data.api.TheSportDBApi
import com.khoiron.footballmatchschedule.data.model.eventdetail.EventDetailResponse
import com.khoiron.footballmatchschedule.data.model.team.TeamResponse
import com.khoiron.footballmatchschedule.ui.detail.DetailView
import com.khoiron.footballmatchschedule.util.context.CoroutinesContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by Khoiron14 on 21/11/18.
 */
class EventDetailPresenter(
    private val view: DetailView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutinesContextProvider = CoroutinesContextProvider()
) {
    fun getEventDetail(eventId: String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getEventDetail(eventId)).await(),
                EventDetailResponse::class.java
            )

            view.showEventDetailList(data.events)
            view.hideLoading()
        }
    }

    fun getHomeTeam(teamId: String?) {
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getTeam(teamId)).await(),
                TeamResponse::class.java
            )

            view.showHomeTeam(data.teams)
        }
    }

    fun getAwayTeam(teamId: String?) {
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getTeam(teamId)).await(),
                TeamResponse::class.java
            )

            view.showAwayTeam(data.teams)
        }
    }
}