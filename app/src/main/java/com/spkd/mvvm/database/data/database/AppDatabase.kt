package com.spkd.mvvm.database.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.spkd.mvvm.database.data.dao.ItemDao
import com.spkd.mvvm.database.data.entitiy.Item

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
}
