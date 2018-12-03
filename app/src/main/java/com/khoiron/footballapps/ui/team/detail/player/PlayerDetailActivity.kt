package com.khoiron.footballapps.ui.team.detail.player

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.google.gson.Gson
import com.khoiron.footballapps.R
import com.khoiron.footballapps.data.api.ApiRepository
import com.khoiron.footballapps.data.model.player.PlayerDetail
import com.khoiron.footballapps.presenter.player.PlayerDetailPresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_player_detail.*

class PlayerDetailActivity : AppCompatActivity(), PlayerDetailView {

    private lateinit var presenter: PlayerDetailPresenter
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        id = intent.getStringExtra("playerId")

        presenter = PlayerDetailPresenter(this, ApiRepository(), Gson())
        presenter.getPlayerDetail(id)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showPlayerDetail(data: List<PlayerDetail>) {
        Picasso.get().load(data[0].playerFanart).into(img_player_fanart)
        txt_player_weight.text = data[0].palyerWeight
        txt_palyer_height.text = data[0].playerHeight
        txt_player_position.text = data[0].playerPosition
        txt_player_description.text = data[0].playerDescription
        supportActionBar?.title = data[0].playerName
    }
}
