package com.khoiron.footballapps.data.model.team

import com.google.gson.annotations.SerializedName

/**
 * Created by Khoiron14 on 29/11/18.
 */
data class Team(
    @SerializedName("idTeam")
    var teamId: String? = null,

    @SerializedName("strTeam")
    var teamName: String? = null,

    @SerializedName("strTeamBadge")
    var teamBadge: String? = null
)