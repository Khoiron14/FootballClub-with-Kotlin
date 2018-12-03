package com.khoiron.footballapps.ui.team.detail.player

import com.khoiron.footballapps.data.model.player.PlayerDetail

/**
 * Created by Khoiron14 on 30/11/18.
 */
interface PlayerDetailView {
    fun showPlayerDetail(data: List<PlayerDetail>)
}