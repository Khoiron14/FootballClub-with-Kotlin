package com.khoiron.footballapps.ui

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.khoiron.footballapps.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_matches -> {
                message.setText(R.string.title_matches)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_teams -> {
                message.setText(R.string.title_teams)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorites -> {
                message.setText(R.string.title_favorites)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
