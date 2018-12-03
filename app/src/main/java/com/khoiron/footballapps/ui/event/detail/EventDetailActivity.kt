package com.khoiron.footballapps.ui.event.detail

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import com.khoiron.footballapps.R
import com.khoiron.footballapps.R.id.add_to_favorite
import com.khoiron.footballapps.R.menu.detail
import com.khoiron.footballapps.data.api.ApiRepository
import com.khoiron.footballapps.data.database
import com.khoiron.footballapps.data.model.event.Event
import com.khoiron.footballapps.data.model.event.EventDetail
import com.khoiron.footballapps.data.model.favorite.FavoriteEvent
import com.khoiron.footballapps.data.model.team.Team
import com.khoiron.footballapps.presenter.event.EventDetailPresenter
import com.khoiron.footballapps.util.convertDateTime
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_event_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.onRefresh
import java.text.SimpleDateFormat
import java.util.*

class EventDetailActivity : AppCompatActivity(), EventDetailView {

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var presenter: EventDetailPresenter
    private lateinit var events: Event
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)

        id = intent.getStringExtra("eventId")

        swipeRefresh = swipe_refresh

        favoriteState()

        presenter = EventDetailPresenter(this, ApiRepository(), Gson())
        presenter.getEventDetail(id)

        swipeRefresh.onRefresh {
            presenter.getEventDetail(id)
        }

        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if (this::events.isInitialized) {
                    if (isFavorite) removeFromFavorite() else addToFavorite()

                    isFavorite = !isFavorite
                    setFavorite()
                }

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showLoading() {
        swipeRefresh.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefresh.isRefreshing = false
    }

    override fun showEventDetail(data: List<EventDetail>) {
        events = Event(
            data[0].eventId,
            data[0].homeTeamId,
            data[0].awayTeamId,
            data[0].homeTeamName,
            data[0].awayTeamName,
            data[0].homeScore,
            data[0].awayScore,
            data[0].eventDate,
            data[0].eventTime
        )

        val formatDate = SimpleDateFormat("EEE, dd MMM yyyy", Locale.getDefault())
        val formatTime = SimpleDateFormat("HH:mm", Locale.getDefault())
        val dateTimeConvert = convertDateTime(data[0].eventDate, data[0].eventTime)

        txt_date.text = formatDate.format(dateTimeConvert)
        txt_time.text = formatTime.format(dateTimeConvert)
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

        swipeRefresh.isRefreshing = false
    }

    override fun showHomeTeam(data: List<Team>) {
        Picasso.get().load(data[0].teamBadge).into(img_home)
    }

    override fun showAwayTeam(data: List<Team>) {
        Picasso.get().load(data[0].teamBadge).into(img_away)
    }

    private fun favoriteState() {
        database.use {
            val result = select(FavoriteEvent.TABLE_EVENT_FAVORITE)
                .whereArgs(
                    "(EVENT_ID = {id})",
                    "id" to id
                )
            val favorite = result.parseList(classParser<FavoriteEvent>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(
                    FavoriteEvent.TABLE_EVENT_FAVORITE,
                    FavoriteEvent.EVENT_ID to events.eventId,
                    FavoriteEvent.HOME_TEAM_ID to events.homeTeamId,
                    FavoriteEvent.AWAY_TEAM_ID to events.awayTeamId,
                    FavoriteEvent.HOME_TEAM_NAME to events.homeTeamName,
                    FavoriteEvent.AWAY_TEAM_NAME to events.awayTeamName,
                    FavoriteEvent.HOME_SCORE to events.homeScore,
                    FavoriteEvent.AWAY_SCORE to events.awayScore,
                    FavoriteEvent.EVENT_DATE to events.eventDate,
                    FavoriteEvent.EVENT_TIME to events.eventTime
                )
            }
            swipeRefresh.snackbar("Added to favorite").show()
        } catch (e: SQLiteConstraintException) {
            swipeRefresh.snackbar(e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(FavoriteEvent.TABLE_EVENT_FAVORITE, "(EVENT_ID = {id})", "id" to id)
            }
            swipeRefresh.snackbar("Removed to favorite").show()
        } catch (e: SQLiteConstraintException) {
            swipeRefresh.snackbar(e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }
}
