package com.example.five.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [SavedArtworkEntity::class], version = 1, exportSchema = true)
@TypeConverters(StringListConverter::class)
abstract class SavedArtworkDatabase : RoomDatabase() {
    abstract fun savedArtworkDao(): SavedArtworkDao
}