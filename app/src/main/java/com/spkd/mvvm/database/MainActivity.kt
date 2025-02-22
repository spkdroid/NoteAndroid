package com.spkd.mvvm.database

import com.spkd.mvvm.database.data.entitiy.Item
import com.spkd.mvvm.database.ui.adapter.ItemAdapter
import com.spkd.mvvm.database.ui.viewmodel.ItemViewModel

@dagger.hilt.android.AndroidEntryPoint
class MainActivity : androidx.appcompat.app.AppCompatActivity() {
    private lateinit var viewModel: ItemViewModel
    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(android.widget.LinearLayout(this).apply {
            orientation = android.widget.LinearLayout.VERTICAL
            val recyclerView = androidx.recyclerview.widget.RecyclerView(context).apply {
                layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            }
            val button = android.widget.Button(context).apply {
                text = "Add Item"
                setOnClickListener { viewModel.insert(Item(name = "Item " + (1..100).random())) }
            }
            addView(button)
            addView(recyclerView)
            adapter = ItemAdapter { viewModel.delete(it) }
            recyclerView.adapter = adapter
        })

        viewModel = androidx.lifecycle.ViewModelProvider(this)[ItemViewModel::class.java]
        viewModel.allItems.observe(this) { adapter.submitList(it) }
    }
}
