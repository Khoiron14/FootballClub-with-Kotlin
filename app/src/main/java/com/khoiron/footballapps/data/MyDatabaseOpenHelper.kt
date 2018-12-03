package com.khoiron.footballapps.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.khoiron.footballapps.data.model.favorite.FavoriteEvent
import com.khoiron.footballapps.data.model.favorite.FavoriteTeam
import org.jetbrains.anko.db.*

/**
 * Created by Khoiron14 on 02/12/18.
 */
class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "Favorite.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(
            FavoriteEvent.TABLE_EVENT_FAVORITE, true,
            FavoriteEvent.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteEvent.EVENT_ID to TEXT + UNIQUE,
            FavoriteEvent.HOME_TEAM_ID to TEXT,
            FavoriteEvent.AWAY_TEAM_ID to TEXT,
            FavoriteEvent.HOME_TEAM_NAME to TEXT,
            FavoriteEvent.AWAY_TEAM_NAME to TEXT,
            FavoriteEvent.HOME_SCORE to TEXT,
            FavoriteEvent.AWAY_SCORE to TEXT,
            FavoriteEvent.EVENT_DATE to TEXT,
            FavoriteEvent.EVENT_TIME to TEXT
        )

        db?.createTable(
            FavoriteTeam.TABLE_TEAM_FAVORITE, true,
            FavoriteTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteTeam.TEAM_ID to TEXT + UNIQUE,
            FavoriteTeam.TEAM_NAME to TEXT,
            FavoriteTeam.TEAM_BADGE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(FavoriteEvent.TABLE_EVENT_FAVORITE, true)
        db?.dropTable(FavoriteTeam.TABLE_TEAM_FAVORITE, true)
    }
}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)