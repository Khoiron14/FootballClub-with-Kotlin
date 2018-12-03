package com.khoiron.footballapps.presenter.player

import com.google.gson.Gson
import com.khoiron.footballapps.data.api.ApiRepository
import com.khoiron.footballapps.data.api.TheSportDBApi
import com.khoiron.footballapps.data.model.player.PlayerDetailResponse
import com.khoiron.footballapps.ui.team.detail.player.PlayerDetailView
import com.khoiron.footballapps.util.context.CoroutinesContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by Khoiron14 on 30/11/18.
 */
class PlayerDetailPresenter(
    private val view: PlayerDetailView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutinesContextProvider = CoroutinesContextProvider()
) {
    fun getPlayerDetail(playerId: String?) {
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getPlayerDetail(playerId)).await(),
                PlayerDetailResponse::class.java
            )

            view.showPlayerDetail(data.players)
        }
    }
}