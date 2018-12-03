package com.khoiron.footballapps.ui.favorite.team


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
import com.khoiron.footballapps.data.model.favorite.FavoriteTeam
import com.khoiron.footballapps.ui.team.detail.TeamDetailActivity
import kotlinx.android.synthetic.main.fragment_favorite_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onRefresh

/**
 * A simple [Fragment] subclass.
 *
 */
class FavoriteTeamFragment : Fragment() {

    private var favorites: MutableList<FavoriteTeam> = mutableListOf()
    private lateinit var adapter: FavoriteTeamAdapter
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var rListTeam: RecyclerView

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        swipeRefresh = swipe_refresh
        rListTeam = list_team

        adapter = FavoriteTeamAdapter(favorites) {
            context?.startActivity<TeamDetailActivity>("teamId" to "${it.teamId}")
        }

        rListTeam.layoutManager = LinearLayoutManager(context)
        rListTeam.adapter = adapter

        swipeRefresh.onRefresh {
            showFavoriteTeam()
        }
    }

    override fun onResume() {
        super.onResume()
        showFavoriteTeam()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_team, container, false)
    }

    private fun showFavoriteTeam() {
        swipeRefresh.isRefreshing = true
        favorites.clear()
        context?.database?.use {
            val result = select(FavoriteTeam.TABLE_TEAM_FAVORITE)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
            swipeRefresh.isRefreshing = false
        }
    }
}
