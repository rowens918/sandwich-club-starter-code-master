package com.udacity.sandwichclub

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.udacity.sandwichclub.model.Sandwich
import com.udacity.sandwichclub.utils.JsonUtils
import com.squareup.picasso.Picasso
import android.widget.Toast
import android.widget.TextView
import java.lang.StringBuilder

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val imgIv = findViewById<ImageView>(R.id.image_iv)
        val intent = intent
        if (intent == null) {
            closeOnError()
        }
        val position = intent!!.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION)
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError()
            return
        }
        val sandwiches = resources.getStringArray(R.array.sandwich_details)
        val json = sandwiches[position]
        val sandwich = JsonUtils.parseSandwichJson(json)
        populateUI(sandwich)
        Picasso.with(this)
            .load(sandwich.image)
            .into(imgIv)
        title = sandwich.mainName
    }

    private fun closeOnError() {
        finish()
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show()
    }

    private fun populateUI(wich: Sandwich) {
        val originTv = findViewById<TextView>(R.id.origin_tv)
        val akaTv = findViewById<TextView>(R.id.also_known_tv)
        val ingTv = findViewById<TextView>(R.id.ingredients_tv)
        val descTv = findViewById<TextView>(R.id.description_tv)
        val akaList = wich.alsoKnownAs
        val sbAka = StringBuilder()
        val ingList = wich.ingredients
        val sbIng = StringBuilder()
        if (akaList != null) {
            for (aka in akaList) {
                sbAka.append(aka)
                sbAka.append(", ")
            }
            sbAka.delete(sbAka.length - 2, sbAka.length)
        } else {
            sbAka.append("")
        }
        if (ingList != null) {
            for (ing in ingList) {
                sbIng.append(ing)
                sbIng.append(", ")
            }
            sbIng.delete(sbIng.length - 2, sbIng.length)
        } else {
            sbIng.append("")
        }
        descTv.text = wich.description
        originTv.text = wich.placeOfOrigin
        akaTv.text = sbAka.toString()
        ingTv.text = sbIng.toString()
    }

    companion object {
        const val EXTRA_POSITION = "extra_position"
        private const val DEFAULT_POSITION = -1
    }
}