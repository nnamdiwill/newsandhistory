package com.example.newsandhistory.prefs

import kotlinx.coroutines.flow.Flow


interface NewsPrefs {
    fun getLocalStorageEnabled(): Flow<Boolean>
    fun getFavoritesFeatureEnabled(): Flow<Boolean>
    fun getRotationEnabled(): Flow<Boolean>

    suspend fun toggleLocalStorage()
    suspend fun toggleFavoritesFeature()
    suspend fun toggleRotation()

}