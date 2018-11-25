package com.khoiron.footballmatchschedule.ui.favoritematch


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.khoiron.footballmatchschedule.R
import com.khoiron.footballmatchschedule.data.database
import com.khoiron.footballmatchschedule.data.model.favorite.Favorite
import com.khoiron.footballmatchschedule.ui.detail.EventDetailActivity
import kotlinx.android.synthetic.main.fragment_favorite_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onRefresh

/**
 * A simple [Fragment] subclass.
 *
 */
class FavoriteMatchFragment : Fragment() {

    private var favorites: MutableList<Favorite> = mutableListOf()
    private lateinit var adapter: FavoriteMatchAdapter

    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        swipeRefresh = swipe_refresh

        adapter = FavoriteMatchAdapter(favorites) {
            context?.startActivity<EventDetailActivity>(EventDetailActivity.EVENT_ID to it.eventId)
        }

        list_event.layoutManager = LinearLayoutManager(activity)
        list_event.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        showFavorite()

        swipeRefresh.onRefresh {
            showFavorite()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_match, container, false)
    }

    private fun showFavorite() {
        favorites.clear()
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

}
