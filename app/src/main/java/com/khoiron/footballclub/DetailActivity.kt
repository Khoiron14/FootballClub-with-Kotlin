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
        DetailActivityUI(item).setContentView(this)
    }

    class DetailActivityUI(val item: FootballClub) : AnkoComponent<DetailActivity> {
        override fun createView(ui: AnkoContext<DetailActivity>) = with(ui) {
            verticalLayout {
                padding = dip(16)

                imageView {
                    item.image?.let { Picasso.get().load(it).into(this) }
                }.lparams {
                    gravity = Gravity.CENTER_HORIZONTAL
                    width = 200
                    height = 200

                    margin = dip(16)
                }

                textView {
                    text = item.name
                    textSize = 18f
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                }.lparams {
                    width = matchParent
                    height = wrapContent
                    bottomMargin = dip(16)
                }

                textView {
                    text = item.description
                    textSize = 16f
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                }.lparams {
                    width = matchParent
                    height = wrapContent
                }
            }
        }

    }
}
