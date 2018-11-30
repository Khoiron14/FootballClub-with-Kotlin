package com.khoiron.footballapps.presenter.team

import com.google.gson.Gson
import com.khoiron.footballapps.data.api.ApiRepository
import com.khoiron.footballapps.data.api.TheSportDBApi
import com.khoiron.footballapps.data.model.team.TeamDetailResponse
import com.khoiron.footballapps.ui.team.TeamDetailView
import com.khoiron.footballapps.util.context.CoroutinesContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by Khoiron14 on 30/11/18.
 */
class TeamDetailPresenter(
    private val view: TeamDetailView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutinesContextProvider = CoroutinesContextProvider()
) {
    fun getTeamDetail(teamId: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getTeamDetail(teamId)).await(),
                TeamDetailResponse::class.java
            )

            view.showTeamDetail(data.teams)
            view.hideLoading()
        }
    }
}