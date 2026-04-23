package com.example.newsandhistory.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsandhistory.dataclasses.CurrentNews


private const val DATABASE_NAME = "currentnews_database"
private const val DATABASE_VERSION = 1

@Database(
    entities = [CurrentNews::class],
    version = DATABASE_VERSION,
    exportSchema = true
)
abstract class CurrentDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDAO

    companion object {
        fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            CurrentDatabase::class.java,
            DATABASE_NAME

        ).build()
    }
}







