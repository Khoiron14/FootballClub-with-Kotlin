package com.khoiron.footballmatchschedule.ui.lastmatch

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.google.gson.Gson
import com.khoiron.footballmatchschedule.R
import com.khoiron.footballmatchschedule.data.api.ApiRepository
import com.khoiron.footballmatchschedule.data.model.event.Event
import com.khoiron.footballmatchschedule.presenter.LastMatchPresenter
import com.khoiron.footballmatchschedule.ui.detail.EventDetailActivity
import com.khoiron.footballmatchschedule.ui.main.MainView
import com.khoiron.footballmatchschedule.util.invisible
import com.khoiron.footballmatchschedule.util.visible
import kotlinx.android.synthetic.main.fragment_last_match.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * A simple [Fragment] subclass.
 *
 */
class LastMatchFragment : Fragment(), MainView {

    private val events: MutableList<Event> = mutableListOf()
    private lateinit var presenter: LastMatchPresenter
    private lateinit var adapter: LastMatchAdapter

    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_last_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = progress_bar
        presenter = LastMatchPresenter(this, ApiRepository(), Gson())
        presenter.getLastMatchList()

        adapter = LastMatchAdapter(events) {
            startActivity<EventDetailActivity>(EventDetailActivity.EVENT_ID to it.eventId)
        }

        list_event.layoutManager = LinearLayoutManager(activity)
        list_event.adapter = adapter
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showEventList(data: List<Event>) {
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }
}
