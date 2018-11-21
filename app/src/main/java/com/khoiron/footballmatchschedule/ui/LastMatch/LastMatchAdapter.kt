package com.khoiron.footballmatchschedule.ui.LastMatch

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khoiron.footballmatchschedule.R
import kotlinx.android.synthetic.main.event_item.view.*
import com.khoiron.footballmatchschedule.data.model.Event.Event
import java.text.SimpleDateFormat

/**
 * Created by Khoiron14 on 20/11/18.
 */
class LastMatchAdapter (private val events: List<Event>) : RecyclerView.Adapter<LastMatchViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): LastMatchViewHolder {
        return LastMatchViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.event_item, p0, false))
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(p0: LastMatchViewHolder, p1: Int) {
        p0.bindItem(events[p1])
    }
}

class LastMatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bindItem(events: Event) {
        val formatDate = SimpleDateFormat("EEE, dd MMM yyyy")
        itemView.txt_date.text = formatDate.format(events.eventDate)
        itemView.txt_home_name.text = events.homeTeamName
        itemView.txt_away_name.text = events.awayTeamName
        itemView.txt_home_score.text = events.homeScore
        itemView.txt_away_score.text = events.awayScore
    }
}