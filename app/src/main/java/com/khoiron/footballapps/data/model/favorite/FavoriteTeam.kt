package com.khoiron.footballapps.data.model.favorite

/**
 * Created by Khoiron14 on 03/12/18.
 */
class FavoriteTeam(
    val id: Long?,
    val teamId: String?,
    val teamName: String?,
    val teamBadge: String?
) {
    companion object {
        const val TABLE_TEAM_FAVORITE: String = "TABLE_TEAM_FAVORITE"
        const val ID: String = "ID_"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val TEAM_BADGE: String = "TEAM_BADGE"
    }
}