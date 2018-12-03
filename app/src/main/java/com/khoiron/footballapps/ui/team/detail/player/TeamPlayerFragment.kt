package com.khoiron.footballapps.ui.team.detail.player


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.khoiron.footballapps.R

/**
 * A simple [Fragment] subclass.
 *
 */
class TeamPlayerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_player, container, false)
    }


}
