package com.khoiron.footballclub

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import com.khoiron.footballclub.models.FootballClub
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class DetailActivity : AppCompatActivity() {

    companion object {
        val ITEM = "item"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val item: FootballClub = intent.getParcelableExtra("item")
        detailActivityUI(item)

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun detailActivityUI(item: FootballClub) {
        verticalLayout {
            padding = dip(16)

            imageView {
                item.image?.let { Picasso.get().load(it).into(this) }
            }.lparams(dip(150), dip(150)) {
                gravity = Gravity.CENTER_HORIZONTAL
                margin = dip(16)
            }

            textView {
                text = item.name
                textSize = 18f
                textAlignment = View.TEXT_ALIGNMENT_CENTER
            }.lparams(matchParent, wrapContent) {
                bottomMargin = dip(16)
            }

            textView {
                text = item.description
                textSize = 16f
                textAlignment = View.TEXT_ALIGNMENT_CENTER
            }.lparams(matchParent, wrapContent)
        }
    }
}