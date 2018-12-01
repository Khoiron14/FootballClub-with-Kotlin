package com.khoiron.footballapps.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.khoiron.footballapps.R
import com.khoiron.footballapps.R.id.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                navigation_match -> {
                    loadMatchFragment(savedInstanceState)
                }
                navigation_team -> {
                    loadTeamFragment(savedInstanceState)
                }
                navigation_favorite -> {
                    loadFavoriteFragment(savedInstanceState)
                }
            }
            true
        }
        navigation.selectedItemId = navigation_match
    }

    private fun loadMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, MatchFragment(), MatchFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadTeamFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, TeamFragment(), TeamFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadFavoriteFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, FavoriteFragment(), FavoriteFragment::class.java.simpleName)
                .commit()
        }
    }
}
