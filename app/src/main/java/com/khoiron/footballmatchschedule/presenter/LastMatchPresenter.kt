package com.khoiron.footballmatchschedule.presenter

import com.google.gson.Gson
import com.khoiron.footballmatchschedule.data.api.ApiRepository
import com.khoiron.footballmatchschedule.data.api.TheSportDBApi
import com.khoiron.footballmatchschedule.data.model.event.EventResponse
import com.khoiron.footballmatchschedule.ui.main.MainView
import com.khoiron.footballmatchschedule.util.context.CoroutinesContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by Khoiron14 on 20/11/18.
 */
class LastMatchPresenter(
    private val view: MainView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutinesContextProvider = CoroutinesContextProvider()
) {
    fun getLastMatchList() {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getLastEvents()).await(),
                EventResponse::class.java
            )

            view.showEventList(data.events)
            view.hideLoading()
        }
    }
}