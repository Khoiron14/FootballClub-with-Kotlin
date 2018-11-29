package com.khoiron.footballapps.data.model.player

import com.google.gson.annotations.SerializedName

/**
 * Created by Khoiron14 on 29/11/18.
 */
data class Player(
    @SerializedName("idPlayer")
    var playerId: String? = null,

    @SerializedName("strCutout")
    var playerCutout: String? = null,

    @SerializedName("strPlayer")
    var playerName: String? = null,

    @SerializedName("strPosition")
    var playerPosition: String? = null
)