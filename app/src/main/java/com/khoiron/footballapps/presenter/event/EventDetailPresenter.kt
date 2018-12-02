package com.khoiron.footballapps.presenter.event

import android.util.Log
import com.google.gson.Gson
import com.khoiron.footballapps.data.api.ApiRepository
import com.khoiron.footballapps.data.api.TheSportDBApi
import com.khoiron.footballapps.data.model.event.EventDetailResponse
import com.khoiron.footballapps.data.model.team.TeamResponse
import com.khoiron.footballapps.ui.event.EventDetailView
import com.khoiron.footballapps.util.context.CoroutinesContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by Khoiron14 on 30/11/18.
 */
class EventDetailPresenter(
    private val view: EventDetailView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutinesContextProvider = CoroutinesContextProvider()
) {
    fun getEventDetail(eventId: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getEventDetail(eventId)).await(),
                EventDetailResponse::class.java
            )

            view.showEventDetail(data.events)
            view.hideLoading()
        }
    }

    fun getHomeTeam(teamId: String?) {
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getTeamDetail(teamId)).await(),
                TeamResponse::class.java
            )

            view.showHomeTeam(data.teams)
        }
    }

    fun getAwayTeam(teamId: String?) {
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getTeamDetail(teamId)).await(),
                TeamResponse::class.java
            )

            view.showAwayTeam(data.teams)
        }
    }
}