package com.khoiron.footballmatchschedule.ui.main

import com.khoiron.footballmatchschedule.data.model.event.Event

/**
 * Created by Khoiron14 on 20/11/18.
 */
interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<Event>)
}