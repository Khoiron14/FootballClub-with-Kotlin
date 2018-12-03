package com.khoiron.footballapps.ui.favorite.team

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khoiron.footballapps.R
import com.khoiron.footballapps.data.model.favorite.FavoriteTeam
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_team.view.*

/**
 * Created by Khoiron14 on 03/12/18.
 */
class FavoriteTeamAdapter(private val favorites: List<FavoriteTeam>, private val listener: (FavoriteTeam) -> Unit) :
    RecyclerView.Adapter<FavoriteTeamViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FavoriteTeamViewHolder {
        val view: View = LayoutInflater.from(p0.context).inflate(R.layout.item_team, p0, false)
        val result = FavoriteTeamViewHolder(view)

        view.setOnClickListener {
            val position = result.adapterPosition

            if (position != RecyclerView.NO_POSITION) {
                val favorite: FavoriteTeam = favorites[position]
                listener(favorite)
            }
        }
        return result
    }

    override fun getItemCount(): Int = favorites.size

    override fun onBindViewHolder(p0: FavoriteTeamViewHolder, p1: Int) {
        p0.bindItem(favorites[p1])
    }

}

class FavoriteTeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bindItem(favorites: FavoriteTeam) {
        Picasso.get().load(favorites.teamBadge).into(itemView.img_team_badge)
        itemView.txt_team_name.text = favorites.teamName
    }
}