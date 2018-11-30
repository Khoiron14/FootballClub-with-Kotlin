package com.khoiron.footballapps.presenter.player

import com.google.gson.Gson
import com.khoiron.footballapps.data.api.ApiRepository
import com.khoiron.footballapps.data.api.TheSportDBApi
import com.khoiron.footballapps.data.model.player.PlayerResponse
import com.khoiron.footballapps.ui.player.PlayerView
import com.khoiron.footballapps.util.context.CoroutinesContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by Khoiron14 on 30/11/18.
 */
class PlayerPresenter(
    private val view: PlayerView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutinesContextProvider = CoroutinesContextProvider()
) {
    fun getPlayerList(teamId: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getPlayers(teamId)).await(),
                PlayerResponse::class.java
            )

            view.showPlayerList(data.players)
            view.hideLoading()
        }
    }
}