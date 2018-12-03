package com.khoiron.footballapps.ui.team.detail.player

import com.khoiron.footballapps.data.model.player.Player

/**
 * Created by Khoiron14 on 30/11/18.
 */
interface PlayerView {
    fun showPlayerList(data: List<Player>)
}