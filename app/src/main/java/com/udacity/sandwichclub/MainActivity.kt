package com.udacity.sandwichclub

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AdapterView.OnItemClickListener
import android.content.Intent
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sandwiches = resources.getStringArray(R.array.sandwich_names)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, sandwiches
        )

        // Simplification: Using a ListView instead of a RecyclerView
        val listView = findViewById<ListView>(R.id.sandwiches_listview)
        listView.adapter = adapter
        listView.onItemClickListener =
            OnItemClickListener { _, _, position, _ -> launchDetailActivity(position) }
    }

    private fun launchDetailActivity(position: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_POSITION, position)
        startActivity(intent)
    }
}