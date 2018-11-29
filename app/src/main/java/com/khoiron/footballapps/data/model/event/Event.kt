package com.khoiron.footballapps.data.model.event

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by Khoiron14 on 29/11/18.
 */
data class Event(
    @SerializedName("idEvent")
    var eventId: String? = null,

    @SerializedName("idHomeTeam")
    var homeTeamId: String? = null,

    @SerializedName("idAwayTeam")
    var awayTeamId: String? = null,

    @SerializedName("strHomeTeam")
    var homeTeamName: String? = null,

    @SerializedName("strAwayTeam")
    var awayTeamName: String? = null,

    @SerializedName("intHomeScore")
    var homeScore: String? = null,

    @SerializedName("intAwayScore")
    var awayScore: String? = null,

    @SerializedName("strEvent")
    var eventDate: Date? = null,

    @SerializedName("strTime")
    var eventTime: String? = null
)