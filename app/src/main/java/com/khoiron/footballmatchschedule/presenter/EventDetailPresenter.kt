package com.khoiron.footballmatchschedule.presenter

import com.google.gson.Gson
import com.khoiron.footballmatchschedule.data.api.ApiRepository
import com.khoiron.footballmatchschedule.data.api.TheSportDBApi
import com.khoiron.footballmatchschedule.data.model.eventdetail.EventDetailResponse
import com.khoiron.footballmatchschedule.data.model.team.TeamResponse
import com.khoiron.footballmatchschedule.ui.detail.DetailView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by Khoiron14 on 21/11/18.
 */
class EventDetailPresenter(
    private val view: DetailView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {
    fun getEventDetail(eventId: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getEventDetail(eventId)),
                EventDetailResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showEventDetailList(data.events)
            }
        }
    }

    fun getHomeTeam(teamId: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getTeam(teamId)),
                TeamResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showHomeTeam(data.teams)
            }
        }
    }

    fun getAwayTeam(teamId: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getTeam(teamId)),
                TeamResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showAwayTeam(data.teams)
            }
        }
    }
}