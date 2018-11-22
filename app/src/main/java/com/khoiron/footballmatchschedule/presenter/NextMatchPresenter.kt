package com.khoiron.footballmatchschedule.presenter

import com.google.gson.Gson
import com.khoiron.footballmatchschedule.data.api.ApiRepository
import com.khoiron.footballmatchschedule.data.api.TheSportDBApi
import com.khoiron.footballmatchschedule.data.model.event.EventResponse
import com.khoiron.footballmatchschedule.ui.main.MainView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by Khoiron14 on 21/11/18.
 */
class NextMatchPresenter(private val view: MainView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson) {
    fun getNextMatchList() {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getNextEvents()),
                EventResponse::class.java)

            uiThread {
                view.hideLoading()
                view.showEventList(data.events)
            }
        }
    }
}