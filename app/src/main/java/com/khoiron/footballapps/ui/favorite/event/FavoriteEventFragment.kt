package com.khoiron.footballapps.ui.favorite.event


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khoiron.footballapps.R
import com.khoiron.footballapps.data.database
import com.khoiron.footballapps.data.model.favorite.FavoriteEvent
import com.khoiron.footballapps.ui.event.detail.EventDetailActivity
import kotlinx.android.synthetic.main.fragment_favorite_event.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onRefresh

/**
 * A simple [Fragment] subclass.
 *
 */
class FavoriteEventFragment : Fragment() {

    private var favorites: MutableList<FavoriteEvent> = mutableListOf()
    private lateinit var adapter: FavoriteEventAdapter
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var rListEvent: RecyclerView

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        swipeRefresh = swipe_refresh
        rListEvent = list_event

        adapter = FavoriteEventAdapter(favorites) {
            context?.startActivity<EventDetailActivity>("eventId" to "${it.eventId}")
        }

        rListEvent.layoutManager = LinearLayoutManager(context)
        rListEvent.adapter = adapter

        swipeRefresh.onRefresh {
            showFavoriteEvent()
        }
    }

    override fun onResume() {
        super.onResume()
        showFavoriteEvent()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_event, container, false)
    }

    private fun showFavoriteEvent() {
        swipeRefresh.isRefreshing = true
        favorites.clear()
        context?.database?.use {
            val result = select(FavoriteEvent.TABLE_EVENT_FAVORITE)
            val favorite = result.parseList(classParser<FavoriteEvent>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
            swipeRefresh.isRefreshing = false
        }
    }
}
