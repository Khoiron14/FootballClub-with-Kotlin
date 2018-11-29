package com.khoiron.footballapps.data.model.player

import com.google.gson.annotations.SerializedName

/**
 * Created by Khoiron14 on 29/11/18.
 */
data class PlayerDetail(
    @SerializedName("idPlayer")
    var playerId: String? = null,

    @SerializedName("strPlayer")
    var playerName: String? = null,

    @SerializedName("strFanart1")
    var playerFanart: String? = null,

    @SerializedName("strWeight")
    var palyerWeight: String? = null,

    @SerializedName("strHeight")
    var playerHeight: String? = null,

    @SerializedName("strPosition")
    var playerPosition: String? = null,

    @SerializedName("strDescriptionEN")
    var playerDescription: String? = null
)