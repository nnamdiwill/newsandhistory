package com.example.newsandhistory.prefs

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val STORE_NAME = "news_prefs"

class NewsPrefsImpl @Inject constructor(@ApplicationContext context: Context) : NewsPrefs {

    private val Context.dataStore by preferencesDataStore(name = STORE_NAME)
    private val dataStore = context.dataStore
    override fun getLocalStorageEnabled(): Flow<Boolean> = dataStore.data.catch {
        emit(emptyPreferences())
    }.map {
        it[STORE_KEY_LOCAL_STORAGE] ?: false
    }

    override fun getFavoritesFeatureEnabled(): Flow<Boolean> = dataStore.data.catch {
        emit(emptyPreferences())
    }.map {
        it[STORE_KEY_FAVORITES_FEATURE] ?: false
    }

    override fun getRotationEnabled(): Flow<Boolean> = dataStore.data.catch {
        emit(emptyPreferences())
    }.map {
        it[STORE_KEY_ROTATION] ?: false
    }

    override suspend fun toggleLocalStorage() {
        dataStore.edit {
            it[STORE_KEY_LOCAL_STORAGE] = it[STORE_KEY_LOCAL_STORAGE]?.not() ?: false
        }
    }

    override suspend fun toggleFavoritesFeature() {
        dataStore.edit {
            it[STORE_KEY_FAVORITES_FEATURE] = it[STORE_KEY_FAVORITES_FEATURE]?.not() ?: false
        }
    }

    override suspend fun toggleRotation() {
        dataStore.edit {
            it[STORE_KEY_ROTATION] = it[STORE_KEY_ROTATION]?.not() ?: false
        }
    }

    companion object {
        private val STORE_KEY_LOCAL_STORAGE = booleanPreferencesKey("local_storage")
        private val STORE_KEY_FAVORITES_FEATURE = booleanPreferencesKey("favorites_feature")
        private val STORE_KEY_ROTATION = booleanPreferencesKey("rotation")
    }
}
