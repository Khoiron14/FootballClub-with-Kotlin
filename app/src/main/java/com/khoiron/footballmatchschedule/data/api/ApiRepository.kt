package com.khoiron.footballmatchschedule.data.api

import java.net.URL

/**
 * Created by Khoiron14 on 20/11/18.
 */
class ApiRepository {
    fun doRequest(url: String): String {
        return URL(url).readText()
    }
}