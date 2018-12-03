package com.khoiron.footballapps.ui.team.detail


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.khoiron.footballapps.R
import com.khoiron.footballapps.data.api.ApiRepository
import com.khoiron.footballapps.data.model.team.TeamDetail
import com.khoiron.footballapps.presenter.team.TeamDetailPresenter
import kotlinx.android.synthetic.main.fragment_team_detail_overview.*

/**
 * A simple [Fragment] subclass.
 *
 */
class TeamDetailOverviewFragment : Fragment(), TeamDetailView {

    private lateinit var presenter: TeamDetailPresenter
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        id = activity?.intent!!.getStringExtra("teamId")

        presenter = TeamDetailPresenter(this, ApiRepository(), Gson())
        presenter.getTeamDetail(id)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_detail_overview, container, false)
    }

    override fun showTeamDetail(data: List<TeamDetail>) {
        txt_team_description.text = data[0].teamDescription
    }
}
