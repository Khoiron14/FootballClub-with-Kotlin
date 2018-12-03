package com.khoiron.footballapps.presenter

import com.google.gson.Gson
import com.khoiron.footballapps.data.api.ApiRepository
import com.khoiron.footballapps.data.api.TheSportDBApi
import com.khoiron.footballapps.data.model.player.Player
import com.khoiron.footballapps.data.model.player.PlayerDetail
import com.khoiron.footballapps.data.model.player.PlayerDetailResponse
import com.khoiron.footballapps.data.model.player.PlayerResponse
import com.khoiron.footballapps.presenter.player.PlayerDetailPresenter
import com.khoiron.footballapps.presenter.player.PlayerPresenter
import com.khoiron.footballapps.ui.team.detail.player.PlayerDetailView
import com.khoiron.footballapps.ui.team.detail.player.PlayerView
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
class PlayerPresenterTest {
    @Mock
    private lateinit var playerView: PlayerView

    @Mock
    private lateinit var playerDetailView: PlayerDetailView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var playerPresenter: PlayerPresenter

    @Mock
    private lateinit var playerDetailPresenter: PlayerDetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        playerPresenter = PlayerPresenter(playerView, apiRepository, gson, TestContextProvider())
        playerDetailPresenter = PlayerDetailPresenter(playerDetailView, apiRepository, gson, TestContextProvider())
        apiRepository = ApiRepository()
        gson = Gson()
    }

    @Test
    fun testGetPlayerList() {
        val players: MutableList<Player> = mutableListOf()
        val response = PlayerResponse(players)
        val teamId = "133602"

        GlobalScope.launch {
            `when`(
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getPlayers(teamId)).await(),
                    PlayerResponse::class.java
                )
            ).thenReturn(response)

            playerPresenter.getPlayerList(teamId)

            Mockito.verify(playerView).showPlayerList(players)
        }
    }

    @Test
    fun testGetPlayerDetail() {
        val players: MutableList<PlayerDetail> = mutableListOf()
        val response = PlayerDetailResponse(players)
        val playerId = "34145937"

        GlobalScope.launch {
            `when`(
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getPlayerDetail(playerId)).await(),
                    PlayerDetailResponse::class.java
                )
            ).thenReturn(response)

            playerDetailPresenter.getPlayerDetail(playerId)

            Mockito.verify(playerDetailView).showPlayerDetail(players)
        }
    }
}