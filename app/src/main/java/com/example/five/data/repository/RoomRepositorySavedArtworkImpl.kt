package com.example.five.data.repository

import com.example.five.data.local.SavedArtworkDao
import com.example.five.data.local.SavedArtworkEntity
import com.example.five.savedartworklist.domain.RepositorySavedArtwork
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomRepositorySavedArtworkImpl @Inject constructor (private val savedArtworkDao: SavedArtworkDao): RepositorySavedArtwork {

    override fun getAllSavedArtworks(): Flow<List<SavedArtworkEntity>> {
        return savedArtworkDao.getAllSavedArtworks()
    }

    override suspend fun insertSavedArtwork(savedArtworkEntity: SavedArtworkEntity) {
        return savedArtworkDao.insertSavedArtwork(savedArtworkEntity)
    }

    override suspend fun deleteSavedArtwork(savedArtworkEntity: SavedArtworkEntity) {
        return savedArtworkDao.deleteSavedArtwork(savedArtworkEntity)
    }
}