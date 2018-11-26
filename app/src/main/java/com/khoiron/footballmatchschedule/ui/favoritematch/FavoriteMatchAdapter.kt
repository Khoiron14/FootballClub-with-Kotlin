package com.khoiron.footballmatchschedule.ui.favoritematch

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khoiron.footballmatchschedule.R
import com.khoiron.footballmatchschedule.data.model.favorite.Favorite
import kotlinx.android.synthetic.main.event_item.view.*

/**
 * Created by Khoiron14 on 25/11/18.
 */
class FavoriteMatchAdapter(private val favorites: List<Favorite>, private val listener: (Favorite) -> Unit) :
    RecyclerView.Adapter<FavoriteMatchViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FavoriteMatchViewHolder {
        val view: View = LayoutInflater.from(p0.context).inflate(R.layout.event_item, p0, false)
        val result = FavoriteMatchViewHolder(view)

        view.setOnClickListener {
            val favorite: Favorite = favorites[result.adapterPosition]
            listener(favorite)
        }

        return result
    }

    override fun getItemCount(): Int = favorites.size

    override fun onBindViewHolder(p0: FavoriteMatchViewHolder, p1: Int) {
        p0.bindItem(favorites[p1])
    }
}

class FavoriteMatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bindItem(favorites: Favorite) {
        itemView.txt_date.text = favorites.eventDate
        itemView.txt_home_name.text = favorites.homeTeamName
        itemView.txt_away_name.text = favorites.awayTeamName
        itemView.txt_home_score.text = favorites.homeScore
        itemView.txt_away_score.text = favorites.awayScore
    }
}