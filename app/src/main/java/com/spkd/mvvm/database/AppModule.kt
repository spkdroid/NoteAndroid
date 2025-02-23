package com.spkd.mvvm.database

import androidx.room.Room
import com.spkd.mvvm.database.data.dao.ItemDao
import com.spkd.mvvm.database.data.database.AppDatabase
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@dagger.Module
@dagger.hilt.InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: android.content.Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "app_db").build()

    @Provides
    fun provideItemDao(database: AppDatabase): ItemDao = database.itemDao()
}