package com.khoiron.footballapps.presenter

import com.google.gson.Gson
import com.khoiron.footballapps.data.api.ApiRepository
import com.khoiron.footballapps.data.api.TheSportDBApi
import com.khoiron.footballapps.data.model.event.Event
import com.khoiron.footballapps.data.model.event.EventDetail
import com.khoiron.footballapps.data.model.event.EventDetailResponse
import com.khoiron.footballapps.data.model.event.EventResponse
import com.khoiron.footballapps.data.model.team.Team
import com.khoiron.footballapps.data.model.team.TeamResponse
import com.khoiron.footballapps.presenter.event.EventDetailPresenter
import com.khoiron.footballapps.presenter.event.LastEventPresenter
import com.khoiron.footballapps.presenter.event.NextEventPresenter
import com.khoiron.footballapps.ui.event.detail.EventDetailView
import com.khoiron.footballapps.ui.event.EventView
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
class EventPresenterTest {
    @Mock
    private lateinit var eventView: EventView

    @Mock
    private lateinit var eventDetailView: EventDetailView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var lastEventPresenter: LastEventPresenter

    @Mock
    private lateinit var nextEventPresenter: NextEventPresenter

    @Mock
    private lateinit var eventDetailPresenter: EventDetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        lastEventPresenter = LastEventPresenter(eventView, apiRepository, gson, TestContextProvider())
        nextEventPresenter = NextEventPresenter(eventView, apiRepository, gson, TestContextProvider())
        eventDetailPresenter = EventDetailPresenter(eventDetailView, apiRepository, gson, TestContextProvider())
        apiRepository = ApiRepository()
        gson = Gson()
    }

    @Test
    fun testGetLastEventList() {
        val events: MutableList<Event> = mutableListOf()
        val response = EventResponse(events)
        val leagueId = "4328"

        GlobalScope.launch {
            `when`(
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getLastEvents(leagueId)).await(),
                    EventResponse::class.java
                )
            ).thenReturn(response)

            lastEventPresenter.getLastEventList(leagueId)

            Mockito.verify(eventView).showLoading()
            Mockito.verify(eventView).showEventList(events)
            Mockito.verify(eventView).hideLoading()
        }
    }

    @Test
    fun testGetNextEventList() {
        val events: MutableList<Event> = mutableListOf()
        val response = EventResponse(events)
        val leagueId = "4328"

        GlobalScope.launch {
            `when`(
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getNextEvents(leagueId)).await(),
                    EventResponse::class.java
                )
            ).thenReturn(response)

            nextEventPresenter.getNextEventList(leagueId)

            Mockito.verify(eventView).showLoading()
            Mockito.verify(eventView).showEventList(events)
            Mockito.verify(eventView).hideLoading()
        }
    }

    @Test
    fun testGetEventDetail() {
        val events: MutableList<EventDetail> = mutableListOf()
        val response = EventDetailResponse(events)
        val eventId = "441613"

        GlobalScope.launch {
            `when`(
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getEventDetail(eventId)).await(),
                    EventDetailResponse::class.java
                )
            ).thenReturn(response)

            eventDetailPresenter.getEventDetail(eventId)

            Mockito.verify(eventDetailView).showLoading()
            Mockito.verify(eventDetailView).showEventDetail(events)
            Mockito.verify(eventDetailView).hideLoading()
        }
    }

    @Test
    fun testGetHomeTeam() {
        val teams: MutableList<Team> = mutableListOf()
        val response = TeamResponse(teams)
        val teamId = "133602"

        GlobalScope.launch {
            `when`(
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getTeams(teamId)).await(),
                    TeamResponse::class.java
                )
            ).thenReturn(response)

            eventDetailPresenter.getHomeTeam(teamId)

            Mockito.verify(eventDetailView).showHomeTeam(teams)
        }
    }

    @Test
    fun testGetAwayTeam() {
        val teams: MutableList<Team> = mutableListOf()
        val response = TeamResponse(teams)
        val teamId = "133614"

        GlobalScope.launch {
            `when`(
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getTeams(teamId)).await(),
                    TeamResponse::class.java
                )
            ).thenReturn(response)

            eventDetailPresenter.getAwayTeam(teamId)

            Mockito.verify(eventDetailView).showAwayTeam(teams)
        }
    }
}