package com.spkd.mvvm.database.data.repository

import com.spkd.mvvm.database.data.dao.ItemDao
import com.spkd.mvvm.database.data.entitiy.Item
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ItemRepository @Inject constructor(private val itemDao: ItemDao) {
    val allItems: Flow<List<Item>> = itemDao.getAllItems()

    suspend fun insert(item: Item) = itemDao.insert(item)
    suspend fun delete(item: Item) = itemDao.delete(item)
}