package com.khoiron.footballmatchschedule.data.model.eventdetail

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by Khoiron14 on 21/11/18.
 */
data class EventDetail(
    @SerializedName("idEvent")
    var eventId : String? = null,

    @SerializedName("idHomeTeam")
    var homeTeamId : String? = null,

    @SerializedName("idAwayTeam")
    var awayTeamId : String? = null,

    @SerializedName("strHomeTeam")
    var homeTeamName : String? = null,

    @SerializedName("strAwayTeam")
    var awayTeamName : String? = null,

    @SerializedName("intHomeScore")
    var homeScore : String? = null,

    @SerializedName("intAwayScore")
    var awayScore : String? = null,

    @SerializedName("dateEvent")
    var eventDate : Date? = null,

    @SerializedName("strHomeGoalDetails")
    var homeGoal : String? = null,

    @SerializedName("strAwayGoalDetails")
    var awayGoal : String? = null,

    @SerializedName("intHomeShots")
    var homeShots : String? = null,

    @SerializedName("intAwayShots")
    var awayShots : String? = null,

    @SerializedName("strHomeLineupGoalkeeper")
    var homeGoalkeeper : String? = null,

    @SerializedName("strAwayLineupGoalkeeper")
    var awayGoalkeeper : String? = null,

    @SerializedName("strHomeLineupDefense")
    var homeDefense : String? = null,

    @SerializedName("strAwayLineupDefense")
    var awayDefense : String? = null,

    @SerializedName("strHomeLineupMidfield")
    var homeMidfield : String? = null,

    @SerializedName("strAwayLineupMidfield")
    var awayMidfield : String? = null,

    @SerializedName("strHomeLineupForward")
    var homeForward : String? = null,

    @SerializedName("strAwayLineupForward")
    var awayForward : String? = null,

    @SerializedName("strHomeLineupSubstitutes")
    var homeSubstitutes : String? = null,

    @SerializedName("strAwayLineupSubstitutes")
    var awaySubstitutes : String? = null
)