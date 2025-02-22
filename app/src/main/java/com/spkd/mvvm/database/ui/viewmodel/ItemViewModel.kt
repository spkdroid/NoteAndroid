package com.spkd.mvvm.database.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.spkd.mvvm.database.data.entitiy.Item
import com.spkd.mvvm.database.data.repository.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(private val repository: ItemRepository) : ViewModel() {
    val allItems: LiveData<List<Item>> = repository.allItems.asLiveData()
    fun insert(item: Item) = viewModelScope.launch { repository.insert(item) }
    fun delete(item: Item) = viewModelScope.launch { repository.delete(item) }
}