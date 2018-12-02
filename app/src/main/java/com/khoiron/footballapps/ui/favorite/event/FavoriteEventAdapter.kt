package com.khoiron.footballapps.ui.favorite.event

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khoiron.footballapps.R
import com.khoiron.footballapps.data.model.favorite.FavoriteEvent
import com.khoiron.footballapps.util.convertDateTime
import kotlinx.android.synthetic.main.item_event.view.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Khoiron14 on 02/12/18.
 */
class FavoriteEventAdapter(private val favorites: List<FavoriteEvent>, private val listener: (FavoriteEvent) -> Unit) :
    RecyclerView.Adapter<FavoriteEventViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FavoriteEventViewHolder {
        val view: View = LayoutInflater.from(p0.context).inflate(R.layout.item_event, p0, false)
        val result = FavoriteEventViewHolder(view)

        view.setOnClickListener {
            val position = result.adapterPosition

            if (position != RecyclerView.NO_POSITION) {
                val favorite: FavoriteEvent = favorites[position]
                listener(favorite)
            }
        }
        return result
    }

    override fun getItemCount(): Int = favorites.size

    override fun onBindViewHolder(p0: FavoriteEventViewHolder, p1: Int) {
        p0.bindItem(favorites[p1])
    }
}

class FavoriteEventViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bindItem(favorites: FavoriteEvent) {
        val formatDate = SimpleDateFormat("EEE, dd MMM yyyy", Locale.getDefault())
        val formatTime = SimpleDateFormat("HH:mm", Locale.getDefault())
        val dateTimeConvert = convertDateTime(favorites.eventDate, favorites.eventTime)

        itemView.txt_date.text = formatDate.format(dateTimeConvert)
        itemView.txt_time.text = formatTime.format(dateTimeConvert)
        itemView.txt_home_name.text = favorites.homeTeamName
        itemView.txt_away_name.text = favorites.awayTeamName
        itemView.txt_home_score.text = favorites.homeScore
        itemView.txt_away_score.text = favorites.awayScore
    }
}