package com.khoiron.footballapps.ui.event

import com.khoiron.footballapps.data.model.event.Event


/**
 * Created by Khoiron14 on 30/11/18.
 */
interface EventView {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<Event>)
}