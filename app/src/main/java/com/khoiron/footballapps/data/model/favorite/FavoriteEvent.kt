package com.khoiron.footballapps.data.model.favorite

/**
 * Created by Khoiron14 on 02/12/18.
 */
data class FavoriteEvent(
    val id: Long?,
    val eventId: String?,
    val homeTeamId: String?,
    val awayTeamId: String?,
    val homeTeamName: String?,
    val awayTeamName: String?,
    val homeScore: String?,
    val awayScore: String?,
    val eventDate: String?,
    val eventTime: String?
) {
    companion object {
        const val TABLE_EVENT_FAVORITE: String = "TABLE_EVENT_FAVORITE"
        const val ID: String = "ID_"
        const val EVENT_ID: String = "EVENT_ID"
        const val HOME_TEAM_ID: String = "HOME_TEAM_ID"
        const val AWAY_TEAM_ID: String = "AWAY_TEAM_ID"
        const val HOME_TEAM_NAME: String = "HOME_TEAM_NAME"
        const val AWAY_TEAM_NAME: String = "AWAY_TEAM_NAME"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val EVENT_DATE: String = "EVENT_DATE"
        const val EVENT_TIME: String = "EVENT_TIME"
    }
}