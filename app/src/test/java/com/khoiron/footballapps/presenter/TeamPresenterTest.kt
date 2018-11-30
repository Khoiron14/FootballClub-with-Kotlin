package com.khoiron.footballapps.presenter

import com.google.gson.Gson
import com.khoiron.footballapps.data.api.ApiRepository
import com.khoiron.footballapps.data.api.TheSportDBApi
import com.khoiron.footballapps.data.model.team.Team
import com.khoiron.footballapps.data.model.team.TeamDetail
import com.khoiron.footballapps.data.model.team.TeamDetailResponse
import com.khoiron.footballapps.data.model.team.TeamResponse
import com.khoiron.footballapps.presenter.team.TeamDetailPresenter
import com.khoiron.footballapps.presenter.team.TeamPresenter
import com.khoiron.footballapps.ui.team.TeamDetailView
import com.khoiron.footballapps.ui.team.TeamView
import com.khoiron.footballapps.util.context.TestContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

/**
 * Created by Khoiron14 on 30/11/18.
 */
class TeamPresenterTest {
    @Mock
    private lateinit var teamView: TeamView

    @Mock
    private lateinit var teamDetailView: TeamDetailView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var teamPresenter: TeamPresenter

    @Mock
    private lateinit var teamDetailPresenter: TeamDetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        teamPresenter = TeamPresenter(teamView, apiRepository, gson, TestContextProvider())
        teamDetailPresenter = TeamDetailPresenter(teamDetailView, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetTeamList() {
        val teams: MutableList<Team> = mutableListOf()
        val response = TeamResponse(teams)
        val leagueId = "4328"

        GlobalScope.launch {
            `when`(
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getTeams(leagueId)).await(),
                    TeamResponse::class.java
                )
            ).thenReturn(response)

            teamPresenter.getTeamList(leagueId)

            Mockito.verify(teamView).showLoading()
            Mockito.verify(teamView).showTeamList(teams)
            Mockito.verify(teamView).hideLoading()
        }
    }

    @Test
    fun testGetTeamDetail() {
        val teams: MutableList<TeamDetail> = mutableListOf()
        val response = TeamDetailResponse(teams)
        val teamId = "133602"

        GlobalScope.launch {
            `when`(
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getTeamDetail(teamId)).await(),
                    TeamDetailResponse::class.java
                )
            ).thenReturn(response)

            teamDetailPresenter.getTeamDetail(teamId)

            Mockito.verify(teamDetailView).showLoading()
            Mockito.verify(teamDetailView).showTeamDetail(teams)
            Mockito.verify(teamDetailView).hideLoading()
        }
    }
}