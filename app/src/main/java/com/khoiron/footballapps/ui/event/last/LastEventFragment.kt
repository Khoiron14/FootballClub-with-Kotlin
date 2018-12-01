package com.khoiron.footballapps.ui.event.last


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.gson.Gson
import com.khoiron.footballapps.R
import com.khoiron.footballapps.data.api.ApiRepository
import com.khoiron.footballapps.data.model.event.Event
import com.khoiron.footballapps.presenter.event.LastEventPresenter
import com.khoiron.footballapps.ui.event.EventView
import kotlinx.android.synthetic.main.fragment_last_event.*
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.toast

/**
 * A simple [Fragment] subclass.
 *
 */
class LastEventFragment : Fragment(), EventView {

    private val events: MutableList<Event> = mutableListOf()
    private var leagueId: String = "4328"
    private lateinit var presenter: LastEventPresenter
    private lateinit var adapter: LastEventAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinnerItems = resources.getStringArray(R.array.league_name)
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> {
                        leagueId = "4328"
                        presenter.getLastEventList(leagueId)
                    }
                    1 -> {
                        leagueId = "4329"
                        presenter.getLastEventList(leagueId)
                    }
                    2 -> {
                        leagueId = "4331"
                        presenter.getLastEventList(leagueId)
                    }
                    3 -> {
                        leagueId = "4332"
                        presenter.getLastEventList(leagueId)
                    }
                    4 -> {
                        leagueId = "4334"
                        presenter.getLastEventList(leagueId)
                    }
                    5 -> {
                        leagueId = "4335"
                        presenter.getLastEventList(leagueId)
                    }
                }
            }
        }

        presenter = LastEventPresenter(this, ApiRepository(), Gson())
        presenter.getLastEventList(leagueId)

        adapter = LastEventAdapter(events) {
            toast(it.homeTeamName.toString())
        }

        list_team.layoutManager = LinearLayoutManager(context)
        list_team.adapter = adapter

        swipe_refresh.onRefresh {
            presenter.getLastEventList(leagueId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_last_event, container, false)
    }

    override fun showLoading() {
        swipe_refresh.isRefreshing = true
    }

    override fun hideLoading() {
        swipe_refresh.isRefreshing = false
    }

    override fun showEventList(data: List<Event>) {
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }

}
