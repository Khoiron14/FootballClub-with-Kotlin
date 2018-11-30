package com.khoiron.footballapps.presenter.team

import com.google.gson.Gson
import com.khoiron.footballapps.data.api.ApiRepository
import com.khoiron.footballapps.data.api.TheSportDBApi
import com.khoiron.footballapps.data.model.team.TeamResponse
import com.khoiron.footballapps.ui.team.TeamView
import com.khoiron.footballapps.util.context.CoroutinesContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by Khoiron14 on 30/11/18.
 */
class TeamPresenter(
    private val view: TeamView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutinesContextProvider = CoroutinesContextProvider()
) {
    fun getTeamList(leagueId: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getTeams(leagueId)).await(),
                TeamResponse::class.java
            )

            view.showTeamList(data.teams)
            view.hideLoading()
        }
    }
}