package com.khoiron.footballapps.ui.team


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.gson.Gson

import com.khoiron.footballapps.R
import com.khoiron.footballapps.data.api.ApiRepository
import com.khoiron.footballapps.data.model.team.Team
import com.khoiron.footballapps.presenter.team.TeamPresenter
import kotlinx.android.synthetic.main.fragment_team.*
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.toast

/**
 * A simple [Fragment] subclass.
 *
 */
class TeamFragment : Fragment(), TeamView {

    private val teams: MutableList<Team> = mutableListOf()
    private val filteredTeams: MutableList<Team> = mutableListOf()
    private var leagueName: String = "English Premier League"
    private lateinit var presenter: TeamPresenter
    private lateinit var adapter: TeamAdapter

    init {
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinnerItems = resources.getStringArray(R.array.league_name)
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leagueName = spinner.selectedItem.toString()
                presenter.getTeamList(leagueName)
            }

        }

        presenter = TeamPresenter(this, ApiRepository(), Gson())
        presenter.getTeamList(leagueName)

        adapter = TeamAdapter(filteredTeams) {
            toast(it.teamName.toString())
        }

        list_team.layoutManager = LinearLayoutManager(context)
        list_team.adapter = adapter

        swipe_refresh.onRefresh {
            presenter.getTeamList(leagueName)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.main, menu)
        val searchItem = menu?.findItem(R.id.menu_search)

        if (searchItem != null) {
            val searchView = searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean = true

                override fun onQueryTextChange(p0: String?): Boolean {
                    if (p0!!.isNotEmpty()) {
                        val search: String = p0.toLowerCase()

                        filteredTeams.clear()
                        teams.forEach {
                            if (it.teamName!!.toLowerCase().contains(search)) {
                                filteredTeams.add(it)
                            }
                        }
                        adapter.notifyDataSetChanged()
                    } else {
                        filteredTeams.clear()
                        filteredTeams.addAll(teams)
                        adapter.notifyDataSetChanged()
                    }

                    return true
                }

            })
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun showLoading() {
        swipe_refresh.isRefreshing = true
    }

    override fun hideLoading() {
        swipe_refresh.isRefreshing = false
    }

    override fun showTeamList(data: List<Team>) {
        teams.clear()
        filteredTeams.clear()
        teams.addAll(data)
        filteredTeams.addAll(data)
        adapter.notifyDataSetChanged()
    }
}