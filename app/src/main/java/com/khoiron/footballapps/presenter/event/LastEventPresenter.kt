package com.khoiron.footballapps.presenter.event

import android.util.Log
import com.google.gson.Gson
import com.khoiron.footballapps.data.api.ApiRepository
import com.khoiron.footballapps.data.api.TheSportDBApi
import com.khoiron.footballapps.data.model.event.EventResponse
import com.khoiron.footballapps.ui.event.EventView
import com.khoiron.footballapps.util.context.CoroutinesContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by Khoiron14 on 30/11/18.
 */
class LastEventPresenter(
    private val view: EventView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutinesContextProvider = CoroutinesContextProvider()
) {
    fun getLastEventList(leagueId: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getLastEvents(leagueId)).await(),
                EventResponse::class.java
            )

            view.showEventList(data.events)
            view.hideLoading()
        }
    }
}