package com.khoiron.footballclub

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.khoiron.footballclub.models.FootballClub
import com.khoiron.footballclub.views.FootballClubAdapter
import org.jetbrains.anko.linearLayout
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.wrapContent

class MainActivity : AppCompatActivity() {

    private var items: MutableList<FootballClub> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initData()
        mainActivityUI()
    }

    private fun initData() {
        val name = resources.getStringArray(R.array.club_name)
        val description = resources.getStringArray(R.array.club_description)
        val image = resources.obtainTypedArray(R.array.club_image)

        items.clear()

        for (i in name.indices) {
            items.add(
                FootballClub(
                    name[i],
                    description[i],
                    image.getResourceId(i, 0)
                )
            )
        }

        image.recycle()
    }

    private fun mainActivityUI() {
        linearLayout {
            recyclerView {
                layoutManager = LinearLayoutManager(context)
                adapter = FootballClubAdapter(context, items) {
                    startActivity<DetailActivity>(DetailActivity.ITEM to it)
                }
            }.lparams(matchParent, wrapContent)
        }
    }

}
