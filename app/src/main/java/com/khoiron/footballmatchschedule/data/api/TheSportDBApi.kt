package com.khoiron.footballmatchschedule.data.api

import android.net.Uri
import com.khoiron.footballmatchschedule.BuildConfig

/**
 * Created by Khoiron14 on 20/11/18.
 */
object TheSportDBApi {
    fun getLastEvents(): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventspastleague.php?id=4328"
    }

    fun getNextEvents(): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventsnextleague.php?id=4328"
    }
}