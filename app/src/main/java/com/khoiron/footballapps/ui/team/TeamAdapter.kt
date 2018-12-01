package com.khoiron.footballapps.ui.team

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khoiron.footballapps.R
import com.khoiron.footballapps.data.model.team.Team
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_team.view.*

/**
 * Created by Khoiron14 on 01/12/18.
 */
class TeamAdapter(private val teams: List<Team>, private val listener: (Team) -> Unit) :
    RecyclerView.Adapter<TeamViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TeamViewHolder {
        val view: View = LayoutInflater.from(p0.context).inflate(R.layout.item_team, p0, false)
        val result = TeamViewHolder(view)

        view.setOnClickListener {
            val position = result.adapterPosition

            if (position != null) {
                val team: Team = teams[position]
                listener(team)
            }
        }

        return result
    }

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(p0: TeamViewHolder, p1: Int) {
        p0.bindItem(teams[p1])
    }
}

class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bindItem(teams: Team) {
        Picasso.get().load(teams.teamBadge).into(itemView.img_team_badge)
        itemView.txt_team_name.text = teams.teamName
    }
}