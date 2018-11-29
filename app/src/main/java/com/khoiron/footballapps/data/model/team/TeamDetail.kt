package com.khoiron.footballapps.data.model.team

import com.google.gson.annotations.SerializedName

/**
 * Created by Khoiron14 on 29/11/18.
 */
data class TeamDetail(
    @SerializedName("idTeam")
    var teamId: String? = null,

    @SerializedName("strTeam")
    var teamName: String? = null,

    @SerializedName("strTeamBadge")
    var teamBadge: String? = null,

    @SerializedName("intFormedYear")
    var teamFormedYear: String? = null,

    @SerializedName("strStadium")
    var teamStadium: String? = null,

    @SerializedName("strDescriptionEN")
    var teamDescription: String? = null
)