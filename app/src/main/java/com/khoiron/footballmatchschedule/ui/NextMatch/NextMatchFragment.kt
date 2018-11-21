package com.khoiron.footballmatchschedule.ui.NextMatch


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson

import com.khoiron.footballmatchschedule.R
import com.khoiron.footballmatchschedule.data.api.ApiRepository
import com.khoiron.footballmatchschedule.data.model.Event.Event
import com.khoiron.footballmatchschedule.presenter.NextMatchPresenter
import com.khoiron.footballmatchschedule.ui.MainView
import com.khoiron.footballmatchschedule.util.invisible
import com.khoiron.footballmatchschedule.util.visible
import kotlinx.android.synthetic.main.fragment_next_match.*

/**
 * A simple [Fragment] subclass.
 *
 */
class NextMatchFragment : Fragment(), MainView {

    private val events: MutableList<Event> = mutableListOf()
    private lateinit var presenter: NextMatchPresenter
    private lateinit var adapter: NextMatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_next_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = NextMatchPresenter(this, ApiRepository(), Gson())
        presenter.getNextMatchList()

        adapter = NextMatchAdapter(events)
        list_event.layoutManager = LinearLayoutManager(activity)
        list_event.adapter = adapter
    }

    override fun showLoading() {
        progress_bar.visible()
    }

    override fun hideLoading() {
        progress_bar.invisible()
    }

    override fun showEventList(data: List<Event>) {
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }
}
