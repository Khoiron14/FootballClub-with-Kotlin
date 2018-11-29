package com.khoiron.footballapps.data.api

import com.khoiron.footballapps.BuildConfig

/**
 * Created by Khoiron14 on 29/11/18.
 */
object TheSportDBApi {
    fun getLastEvents(leagueId: String? = "4328"): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventspastleague.php?id=$leagueId"
    }

    fun getNextEvents(leagueId: String? = "4328"): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventsnextleague.php?id=$leagueId"
    }

    fun getEventDetail(eventId: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupevent.php?id=$eventId"
    }

    fun getTeams(leagueId: String? = "4328"): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/search_all_teams.php?l=$leagueId"
    }

    fun getTeamDetail(teamId: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupteam.php?id=$teamId"
    }

    fun getPlayers(teamId: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookup_all_players.php?id=$teamId"
    }

    fun getPlayerDetail(playerId: String): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupplayer.php?id=$playerId"
    }
}