package com.khoiron.footballmatchschedule.ui.lastmatch

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khoiron.footballmatchschedule.R
import com.khoiron.footballmatchschedule.data.model.event.Event
import kotlinx.android.synthetic.main.event_item.view.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Khoiron14 on 20/11/18.
 */
class LastMatchAdapter(private val events: List<Event>, private val listener: (Event) -> Unit) :
    RecyclerView.Adapter<LastMatchViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): LastMatchViewHolder {
        return LastMatchViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.event_item, p0, false))
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(p0: LastMatchViewHolder, p1: Int) {
        p0.bindItem(events[p1], listener)
    }
}

class LastMatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bindItem(events: Event, listener: (Event) -> Unit) {
        val formatDate = SimpleDateFormat("EEE, dd MMM yyyy", Locale.getDefault())
        itemView.txt_date.text = formatDate.format(events.eventDate)
        itemView.txt_home_name.text = events.homeTeamName
        itemView.txt_away_name.text = events.awayTeamName
        itemView.txt_home_score.text = events.homeScore
        itemView.txt_away_score.text = events.awayScore

        itemView.setOnClickListener {
            listener(events)
        }
    }
}