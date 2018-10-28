package com.khoiron.footballclub

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khoiron.footballclub.models.FootballClub
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_list.view.*

class FootballClubAdapter(
    private val context: Context,
    private val items: List<FootballClub>,
    private val listener: (FootballClub) -> Unit
) : RecyclerView.Adapter<FootballClubAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(items: FootballClub, listener: (FootballClub) -> Unit) {
            itemView.txt_name.text = items.name
            items.image?.let { Picasso.get().load(it).into(itemView.img_logo) }

            itemView.setOnClickListener {
                listener(items)
            }
        }
    }
}