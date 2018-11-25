package com.khoiron.footballmatchschedule.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.khoiron.footballmatchschedule.R
import com.khoiron.footballmatchschedule.R.id.*
import com.khoiron.footballmatchschedule.ui.favoritematch.FavoriteMatchFragment
import com.khoiron.footballmatchschedule.ui.lastmatch.LastMatchFragment
import com.khoiron.footballmatchschedule.ui.nextmatch.NextMatchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                last_match -> {
                    loadLastMatchFragment(savedInstanceState)
                }
                next_match -> {
                    loadNextMatchFragment(savedInstanceState)
                }
                favorites -> {
                    loadFavoriteMatchFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = last_match
    }

    private fun loadLastMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, LastMatchFragment(), LastMatchFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadNextMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, NextMatchFragment(), NextMatchFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadFavoriteMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, FavoriteMatchFragment(), FavoriteMatchFragment::class.java.simpleName)
                .commit()
        }
    }
}
