package com.khoiron.footballapps.ui.event.last


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.gson.Gson
import com.khoiron.footballapps.R
import com.khoiron.footballapps.data.api.ApiRepository
import com.khoiron.footballapps.data.model.event.Event
import com.khoiron.footballapps.presenter.event.LastEventPresenter
import com.khoiron.footballapps.ui.event.EventView
import com.khoiron.footballapps.ui.event.detail.EventDetailActivity
import kotlinx.android.synthetic.main.fragment_last_event.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onRefresh

/**
 * A simple [Fragment] subclass.
 *
 */
class LastEventFragment : Fragment(), EventView {

    private val events: MutableList<Event> = mutableListOf()
    private val filteredEvents: MutableList<Event> = mutableListOf()
    private var leagueId: String = "4328"
    private lateinit var presenter: LastEventPresenter
    private lateinit var adapter: LastEventAdapter
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var rListEvent: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        swipeRefresh = swipe_refresh
        rListEvent = list_event

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

        adapter = LastEventAdapter(filteredEvents) {
            context?.startActivity<EventDetailActivity>("eventId" to "${it.eventId}")
        }

        rListEvent.layoutManager = LinearLayoutManager(context)
        rListEvent.adapter = adapter

        swipeRefresh.onRefresh {
            presenter.getLastEventList(leagueId)
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

                        filteredEvents.clear()
                        events.forEach {
                            if (it.homeTeamName!!.toLowerCase().contains(search) or it.awayTeamName!!.toLowerCase().contains(
                                    search
                                )
                            ) {
                                filteredEvents.add(it)
                            }
                        }
                        adapter.notifyDataSetChanged()
                    } else {
                        filteredEvents.clear()
                        filteredEvents.addAll(events)
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
        return inflater.inflate(R.layout.fragment_last_event, container, false)
    }

    override fun showLoading() {
        swipeRefresh.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefresh.isRefreshing = false
    }

    override fun showEventList(data: List<Event>) {
        events.clear()
        filteredEvents.clear()
        events.addAll(data)
        filteredEvents.addAll(data)
        adapter.notifyDataSetChanged()
    }

}
