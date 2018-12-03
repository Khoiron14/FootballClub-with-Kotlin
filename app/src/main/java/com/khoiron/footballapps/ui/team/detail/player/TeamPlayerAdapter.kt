package com.khoiron.footballapps.ui.team.detail.player

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khoiron.footballapps.R
import com.khoiron.footballapps.data.model.player.Player
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_player.view.*

/**
 * Created by Khoiron14 on 03/12/18.
 */
class TeamPlayerAdapter(private val players: List<Player>, private val listener: (Player) -> Unit) :
    RecyclerView.Adapter<TeamPlayerViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TeamPlayerViewHolder {
        val view: View = LayoutInflater.from(p0.context).inflate(R.layout.item_player, p0, false)
        val result = TeamPlayerViewHolder(view)

        view.setOnClickListener {
            val position = result.adapterPosition

            if (position != null) {
                val player: Player = players[position]
                listener(player)
            }
        }

        return result
    }

    override fun getItemCount(): Int = players.size

    override fun onBindViewHolder(p0: TeamPlayerViewHolder, p1: Int) {
        p0.bindItem(players[p1])
    }
}

class TeamPlayerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bindItem(players: Player) {
        Picasso.get().load(players.playerCutout).into(itemView.img_player_cutout)
        itemView.txt_player_name.text = players.playerName
        itemView.txt_player_position.text = players.playerPosition
    }
}