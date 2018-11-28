package com.khoiron.footballmatchschedule

import com.google.gson.Gson
import com.khoiron.footballmatchschedule.data.api.ApiRepository
import com.khoiron.footballmatchschedule.data.api.TheSportDBApi
import com.khoiron.footballmatchschedule.data.model.event.Event
import com.khoiron.footballmatchschedule.data.model.event.EventResponse
import com.khoiron.footballmatchschedule.data.model.eventdetail.EventDetail
import com.khoiron.footballmatchschedule.data.model.eventdetail.EventDetailResponse
import com.khoiron.footballmatchschedule.data.model.team.Team
import com.khoiron.footballmatchschedule.data.model.team.TeamResponse
import com.khoiron.footballmatchschedule.presenter.EventDetailPresenter
import com.khoiron.footballmatchschedule.presenter.LastMatchPresenter
import com.khoiron.footballmatchschedule.presenter.NextMatchPresenter
import com.khoiron.footballmatchschedule.ui.detail.DetailView
import com.khoiron.footballmatchschedule.ui.main.MainView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

/**
 * Created by Khoiron14 on 29/11/18.
 */
class PresenterTest {
    @Mock
    private lateinit var mainView: MainView

    @Mock
    private lateinit var detailView: DetailView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var lastMatchPresenter: LastMatchPresenter

    @Mock
    private lateinit var nextMatchPresenter: NextMatchPresenter

    @Mock
    private lateinit var eventDetailPresenter: EventDetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        lastMatchPresenter = LastMatchPresenter(mainView, apiRepository, gson)
        nextMatchPresenter = NextMatchPresenter(mainView, apiRepository, gson)
        eventDetailPresenter = EventDetailPresenter(detailView, apiRepository, gson)
    }

    @Test
    fun testGetLastMatchList() {
        val events: MutableList<Event> = mutableListOf()
        val response = EventResponse(events)

        GlobalScope.launch {
            `when`(
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getLastEvents()).await(),
                    EventResponse::class.java
                )
            ).thenReturn(response)

            lastMatchPresenter.getLastMatchList()

            Mockito.verify(mainView).showLoading()
            Mockito.verify(mainView).showEventList(events)
            Mockito.verify(mainView).hideLoading()
        }
    }

    @Test
    fun testGetNextMatchList() {
        val events: MutableList<Event> = mutableListOf()
        val response = EventResponse(events)

        GlobalScope.launch {
            `when`(
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getNextEvents()).await(),
                    EventResponse::class.java
                )
            ).thenReturn(response)

            nextMatchPresenter.getNextMatchList()

            Mockito.verify(mainView).showLoading()
            Mockito.verify(mainView).showEventList(events)
            Mockito.verify(mainView).hideLoading()
        }
    }

    @Test
    fun testGetEventDetail() {
        val eventDetails: MutableList<EventDetail> = mutableListOf()
        val response = EventDetailResponse(eventDetails)
        val eventId = "441613"

        GlobalScope.launch {
            `when`(
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getEventDetail(eventId)).await(),
                    EventDetailResponse::class.java
                )
            ).thenReturn(response)

            eventDetailPresenter.getEventDetail(eventId)

            Mockito.verify(detailView).showLoading()
            Mockito.verify(detailView).showEventDetailList(eventDetails)
            Mockito.verify(detailView).hideLoading()
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
                    apiRepository.doRequest(TheSportDBApi.getTeam(teamId)).await(),
                    TeamResponse::class.java
                )
            ).thenReturn(response)

            eventDetailPresenter.getHomeTeam(teamId)

            Mockito.verify(detailView).showHomeTeam(teams)
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
                    apiRepository.doRequest(TheSportDBApi.getTeam(teamId)).await(),
                    TeamResponse::class.java
                )
            ).thenReturn(response)

            eventDetailPresenter.getAwayTeam(teamId)

            Mockito.verify(detailView).showAwayTeam(teams)
        }
    }
}