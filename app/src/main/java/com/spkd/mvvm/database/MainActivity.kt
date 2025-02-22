package com.spkd.mvvm.database

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.spkd.mvvm.database.data.entitiy.Item
import com.spkd.mvvm.database.ui.adapter.ItemAdapter
import com.spkd.mvvm.database.ui.viewmodel.ItemViewModel

@dagger.hilt.android.AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: ItemViewModel by viewModels()
    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        val editText = EditText(this).apply {
            hint = "Enter item name"
        }

        val button = Button(this).apply {
            text = "Add Item"
            setOnClickListener {
                val itemName = editText.text.toString()
                if (itemName.isNotBlank()) {
                    viewModel.insert(Item(name = itemName))
                    editText.text.clear()
                }
            }
        }

        val recyclerView = RecyclerView(this).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        adapter = ItemAdapter { viewModel.delete(it) }
        recyclerView.adapter = adapter

        layout.apply {
            addView(editText)
            addView(button)
            addView(recyclerView)
        }

        setContentView(layout)

        viewModel.allItems.observe(this) { adapter.submitList(it) }
    }
}
