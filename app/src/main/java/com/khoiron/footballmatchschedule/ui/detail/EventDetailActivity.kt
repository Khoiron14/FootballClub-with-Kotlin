package com.khoiron.footballmatchschedule.ui.detail

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import com.khoiron.footballmatchschedule.R
import com.khoiron.footballmatchschedule.R.id.add_to_favorite
import com.khoiron.footballmatchschedule.R.drawable.ic_add_to_favorites
import com.khoiron.footballmatchschedule.R.drawable.ic_added_to_favorites
import com.khoiron.footballmatchschedule.R.menu.detail_menu
import com.khoiron.footballmatchschedule.data.api.ApiRepository
import com.khoiron.footballmatchschedule.data.database
import com.khoiron.footballmatchschedule.data.model.event.Event
import com.khoiron.footballmatchschedule.data.model.eventdetail.EventDetail
import com.khoiron.footballmatchschedule.data.model.favorite.Favorite
import com.khoiron.footballmatchschedule.data.model.team.Team
import com.khoiron.footballmatchschedule.presenter.EventDetailPresenter
import com.khoiron.footballmatchschedule.util.invisible
import com.khoiron.footballmatchschedule.util.visible
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_event_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat
import java.util.*

class EventDetailActivity : AppCompatActivity(), DetailView {
    companion object {
        const val EVENT_ID = "eventId"
    }

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private lateinit var presenter: EventDetailPresenter
    private lateinit var events: Event
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)

        id = intent.getStringExtra("eventId")

        favoriteState()
        presenter = EventDetailPresenter(this, ApiRepository(), Gson())
        presenter.getEventDetail(id)

        swipe_refresh.onRefresh {
            presenter.getEventDetail(id)
        }

        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
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
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun favoriteState() {
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                .whereArgs("(EVENT_ID = {id})",
                    "id" to id)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(
                    Favorite.TABLE_FAVORITE,
                    Favorite.EVENT_ID to events.eventId,
                    Favorite.HOME_TEAM_ID to events.homeTeamId,
                    Favorite.AWAY_TEAM_ID to events.awayTeamId,
                    Favorite.HOME_TEAM_NAME to events.homeTeamName,
                    Favorite.AWAY_TEAM_NAME to events.awayTeamName,
                    Favorite.HOME_SCORE to events.homeScore,
                    Favorite.AWAY_SCORE to events.awayScore,
                    Favorite.EVENT_DATE to events.eventDate.toString()
                )
            }
            swipe_refresh.snackbar("Added to favorite").show()
        } catch (e: SQLiteConstraintException) {
            swipe_refresh.snackbar(e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(EVENT_ID = {id})", "id" to id)
            }
            swipe_refresh.snackbar("Removed to favorite").show()
        } catch (e: SQLiteConstraintException) {
            swipe_refresh.snackbar(e.localizedMessage).show()
        }
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
        events = Event(
            data[0].eventId,
            data[0].homeTeamId,
            data[0].awayTeamId,
            data[0].homeTeamName,
            data[0].awayTeamName,
            data[0].homeScore,
            data[0].awayScore,
            data[0].eventDate
        )
        swipe_refresh.isRefreshing = false
        val formatDate = SimpleDateFormat("EEE, dd MMM yyyy", Locale.getDefault())
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
