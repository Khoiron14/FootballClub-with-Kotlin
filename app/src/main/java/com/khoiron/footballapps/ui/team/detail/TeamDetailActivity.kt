package com.khoiron.footballapps.ui.team.detail

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import com.khoiron.footballapps.R
import com.khoiron.footballapps.R.id.add_to_favorite
import com.khoiron.footballapps.R.menu.detail
import com.khoiron.footballapps.data.api.ApiRepository
import com.khoiron.footballapps.data.database
import com.khoiron.footballapps.data.model.favorite.FavoriteTeam
import com.khoiron.footballapps.data.model.team.Team
import com.khoiron.footballapps.data.model.team.TeamDetail
import com.khoiron.footballapps.presenter.team.TeamDetailPresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar

class TeamDetailActivity : AppCompatActivity(), TeamDetailView {

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var presenter: TeamDetailPresenter
    private lateinit var teams: Team
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)
        setSupportActionBar(toolbar)

        id = intent.getStringExtra("teamId")

        favoriteState()

        presenter = TeamDetailPresenter(this, ApiRepository(), Gson())
        presenter.getTeamDetail(id)

        view_pager.adapter = TeamDetailPagerAdapter(supportFragmentManager)
        tab_layout.setupWithViewPager(view_pager)

        supportActionBar?.setDisplayShowTitleEnabled(false)
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
                if (this::teams.isInitialized) {
                    if (isFavorite) removeFromFavorite() else addToFavorite()

                    isFavorite = !isFavorite
                    setFavorite()
                }

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showTeamDetail(data: List<TeamDetail>) {
        teams = Team(
            data[0].teamId,
            data[0].teamName,
            data[0].teamBadge
        )

        Picasso.get().load(data[0].teamBadge).into(img_team_badge)
        txt_team_name.text = data[0].teamName
        txt_team_formed_year.text = data[0].teamFormedYear
        txt_team_stadium.text = data[0].teamStadium
    }

    private fun favoriteState() {
        database.use {
            val result = select(FavoriteTeam.TABLE_TEAM_FAVORITE)
                .whereArgs(
                    "(TEAM_ID = {id})",
                    "id" to id
                )
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(
                    FavoriteTeam.TABLE_TEAM_FAVORITE,
                    FavoriteTeam.TEAM_ID to teams.teamId,
                    FavoriteTeam.TEAM_NAME to teams.teamName,
                    FavoriteTeam.TEAM_BADGE to teams.teamBadge
                )
            }
            coordinator_layout.snackbar("Added to favorite").show()
        } catch (e: SQLiteConstraintException) {
            coordinator_layout.snackbar(e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(FavoriteTeam.TABLE_TEAM_FAVORITE, "(TEAM_ID = {id})", "id" to id)
            }
            coordinator_layout.snackbar("Removed to favorite").show()
        } catch (e: SQLiteConstraintException) {
            coordinator_layout.snackbar(e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }
}
