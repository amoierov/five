package com.example.five.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedArtworkDao {
    @Query("SELECT * FROM ${SavedArtworkEntity.TABLE_NAME}")
    fun getAllSavedArtworks(): Flow<List<SavedArtworkEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSavedArtwork(savedArtworkEntity: SavedArtworkEntity)

    @Delete
    suspend fun deleteSavedArtwork(savedArtworkEntity: SavedArtworkEntity)
}