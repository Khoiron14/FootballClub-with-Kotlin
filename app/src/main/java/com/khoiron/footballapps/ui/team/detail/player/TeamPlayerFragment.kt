package com.khoiron.footballapps.ui.team.detail.player


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.khoiron.footballapps.R
import com.khoiron.footballapps.data.api.ApiRepository
import com.khoiron.footballapps.data.model.player.Player
import com.khoiron.footballapps.presenter.player.PlayerPresenter
import kotlinx.android.synthetic.main.fragment_team_player.*
import org.jetbrains.anko.startActivity

/**
 * A simple [Fragment] subclass.
 *
 */
class TeamPlayerFragment : Fragment(), PlayerView {

    private val players: MutableList<Player> = mutableListOf()
    private lateinit var presenter: PlayerPresenter
    private lateinit var adapter: TeamPlayerAdapter
    private lateinit var rListPlayer: RecyclerView
    private lateinit var id: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rListPlayer = list_player

        id = activity?.intent?.getStringExtra("teamId") ?: ""

        presenter = PlayerPresenter(this, ApiRepository(), Gson())
        presenter.getPlayerList(id)

        adapter = TeamPlayerAdapter(players) {
            context?.startActivity<PlayerDetailActivity>("playerId" to "${it.playerId}")
        }

        rListPlayer.layoutManager = LinearLayoutManager(context)
        rListPlayer.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_player, container, false)
    }

    override fun showPlayerList(data: List<Player>) {
        players.clear()
        players.addAll(data)
        adapter.notifyDataSetChanged()
    }
}
