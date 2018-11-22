package com.khoiron.footballmatchschedule.data.model.team

import com.google.gson.annotations.SerializedName

/**
 * Created by Khoiron14 on 22/11/18.
 */
data class Team (
    @SerializedName("idTeam")
    var teamId : String? = null,

    @SerializedName("strTeamBadge")
    var teamBadge : String? = null
)