package com.khoiron.footballapps.ui.event.next

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khoiron.footballapps.R
import com.khoiron.footballapps.data.model.event.Event
import com.khoiron.footballapps.util.convertDateTime
import kotlinx.android.synthetic.main.item_event.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Khoiron14 on 01/12/18.
 */
class NextEventAdapter(private val events: List<Event>, private val listener: (Event) -> Unit) :
    RecyclerView.Adapter<NextEventViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): NextEventViewHolder {
        val view: View = LayoutInflater.from(p0.context).inflate(R.layout.item_event, p0, false)
        val result = NextEventViewHolder(view)

        view.setOnClickListener {
            val position = result.adapterPosition

            if (position != RecyclerView.NO_POSITION) {
                val event: Event = events[position]
                listener(event)
            }
        }

        return result
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(p0: NextEventViewHolder, p1: Int) {
        p0.bindItem(events[p1])
    }

}

class NextEventViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bindItem(events: Event) {
        val formatDate = SimpleDateFormat("EEE, dd MMM yyyy", Locale.getDefault())
        val formatTime = SimpleDateFormat("HH:mm", Locale.getDefault())
        val dateTimeConvert = convertDateTime(events.eventDate, events.eventTime)

        itemView.txt_date.text = formatDate.format(dateTimeConvert)
        itemView.txt_time.text = formatTime.format(dateTimeConvert)
        itemView.txt_home_name.text = events.homeTeamName
        itemView.txt_away_name.text = events.awayTeamName
        itemView.txt_home_score.text = events.homeScore
        itemView.txt_away_score.text = events.awayScore
    }
}