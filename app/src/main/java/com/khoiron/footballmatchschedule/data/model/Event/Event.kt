package com.khoiron.footballmatchschedule.data.model.Event

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * Created by Khoiron14 on 20/11/18.
 */
@Parcelize
data class Event (
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
    var eventDate : Date? = null
    ) : Parcelable