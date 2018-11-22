package com.khoiron.footballmatchschedule.ui.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.khoiron.footballmatchschedule.R
import com.khoiron.footballmatchschedule.data.api.ApiRepository
import com.khoiron.footballmatchschedule.data.model.eventdetail.EventDetail
import com.khoiron.footballmatchschedule.data.model.team.Team
import com.khoiron.footballmatchschedule.presenter.EventDetailPresenter
import com.khoiron.footballmatchschedule.util.invisible
import com.khoiron.footballmatchschedule.util.visible
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_event_detail.*
import java.text.SimpleDateFormat

class EventDetailActivity : AppCompatActivity(), DetailView {
    companion object {
        const val EVENT_ID = "eventId"
    }

    private lateinit var presenter: EventDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)

        presenter = EventDetailPresenter(this, ApiRepository(), Gson())
        presenter.getEventDetail(intent.getStringExtra("eventId"))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun showLoading() {
        progress_bar.visible()
    }

    override fun hideLoading() {
        progress_bar.invisible()
    }

    override fun showEventDetailList(data: List<EventDetail>) {
        val formatDate = SimpleDateFormat("EEE, dd MMM yyyy")
        txt_date.text = formatDate.format(data[0].eventDate)
        txt_home_name.text = data[0].homeTeamName
        txt_away_name.text = data[0].awayTeamName
        txt_home_score.text = data[0].homeScore
        txt_away_score.text = data[0].awayScore
        txt_home_goals.text = data[0].homeGoal
        txt_away_goals.text = data[0].awayGoal
        txt_home_shots.text = data[0].homeShots
        txt_away_shots.text = data[0].awayShots
        txt_home_goalkeeper.text = data[0].homeGoalkeeper
        txt_away_goalkeeper.text = data[0].awayGoalkeeper
        txt_home_defense.text = data[0].homeDefense
        txt_away_defense.text = data[0].awayDefense
        txt_home_midfield.text = data[0].homeMidfield
        txt_away_midfield.text = data[0].awayMidfield
        txt_home_forward.text = data[0].homeForward
        txt_away_forward.text = data[0].awayForward

        presenter.getHomeTeam(data[0].homeTeamId)
        presenter.getAwayTeam(data[0].awayTeamId)
    }

    override fun showHomeTeam(data: List<Team>) {
        Picasso.get().load(data[0].teamBadge).into(img_home)
    }

    override fun showAwayTeam(data: List<Team>) {
        Picasso.get().load(data[0].teamBadge).into(img_away)
    }
}
