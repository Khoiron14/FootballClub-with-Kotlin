package com.khoiron.footballclub.views

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.khoiron.footballclub.models.FootballClub
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import org.jetbrains.anko.AnkoContext

class FootballClubAdapter(
    private val context: Context,
    private val items: List<FootballClub>,
    private val listener: (FootballClub) -> Unit
) : RecyclerView.Adapter<FootballClubAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            FootballClubHolder().createView(
                AnkoContext.create(parent.context, parent)
            )
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        val textView: TextView = itemView.findViewById(FootballClubHolder.nameId)
        val imageView: ImageView = itemView.findViewById(FootballClubHolder.imageId)

        fun bindItem(items: FootballClub, listener: (FootballClub) -> Unit) {
            textView.text = items.name
            items.image?.let { Picasso.get().load(it).into(imageView) }

            itemView.setOnClickListener {
                listener(items)
            }
        }
    }
}