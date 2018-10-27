package com.khoiron.footballclub

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.khoiron.footballclub.models.FootballClub
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    private var items: MutableList<FootballClub> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()

        club_list.layoutManager = LinearLayoutManager(this)
        club_list.adapter = RecyclerViewAdapter(this, items) {
            startActivity<DetailActivity>("item" to it)
        }
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
}
