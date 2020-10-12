package com.mbustama.pec1

import android.R.id
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.book_list.*
import kotlinx.android.synthetic.main.fragment_book_detail.*


class BookListActivity : AppCompatActivity() {

    private val ITEMS: MutableList<Item> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.book_list)

        for (i in 1..25) {
            ITEMS.add(Item(i.toString(), "$i    Item $i"))
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ITEMS)
        this.listView.adapter = adapter

        this.listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val item = adapter.getItem(position)

            if ( this.detail_fragment != null) {
                this.detail_fragment.textView.text = "Details about Item: ${position+1} \nMore details information here."

            } else {
                val intent = Intent(this, BookDetailActivity::class.java)
                intent.putExtra("item", item.toString())
                intent.putExtra("position", position.toString())
                startActivity(intent)
            }
        }
    }

    class Item(val id: String, private val item: String) {
        override fun toString(): String = item
    }
}