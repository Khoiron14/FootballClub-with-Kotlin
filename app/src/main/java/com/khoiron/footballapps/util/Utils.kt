package com.khoiron.footballapps.util

import android.view.View
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Khoiron14 on 01/12/18.
 */
fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun convertDateTime(date: String?, time: String?): Date? {
    val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    format.timeZone = TimeZone.getTimeZone("UTC")
    val dateTime = "$date $time"
    return format.parse(dateTime)
}