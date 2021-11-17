package com.caren.simpletodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        var itemlist = arrayListOf<String>()
        var adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, itemlist)

        add.setOnClickListener {
            itemlist.add(editText.text.toString())
            listView.adapter = adapter
            adapter.notifyDataSetChanged()
            android.widget.Toast.makeText(this, "List Added! ", android.widget.Toast.LENGTH_SHORT).show()
            editText.text.clear()
        }

        clear.setOnClickListener {
            android.widget.Toast.makeText(this, "List cleared! ", android.widget.Toast.LENGTH_SHORT).show()
            itemlist.clear()
            adapter.notifyDataSetChanged()
        }

        listView.setOnItemClickListener { _, _, i, l ->
            android.widget.Toast.makeText(this,"Selected " + itemlist.get(i), android.widget.Toast.LENGTH_SHORT).show()
        }

        delete.setOnClickListener {

            val position: SparseBooleanArray = listView.checkedItemPositions
            val count = listView.count
            var item = count - 1

            while (item >= 0) {

                if (position.get(item)) {

                    adapter.remove(itemlist.get(item))

                }

                item--

            }

            android.widget.Toast.makeText(this, "Removed! ", android.widget.Toast.LENGTH_SHORT).show()
            position.clear()
            adapter.notifyDataSetChanged()

        }
    }
}