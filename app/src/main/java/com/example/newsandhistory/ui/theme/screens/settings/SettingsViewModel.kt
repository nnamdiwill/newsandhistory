package com.example.newsandhistory.ui.theme.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsandhistory.prefs.NewsPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SettingsViewModel @Inject constructor(private val prefs: NewsPrefs) : ViewModel() {
    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            prefs.getLocalStorageEnabled()
                .collect {
                    _uiState.value = _uiState.value.copy(localStorageEnabled = it)
                }
        }

        viewModelScope.launch {
            prefs.getFavoritesFeatureEnabled()
                .collect {
                    _uiState.value = _uiState.value.copy(favoritesFeatureEnabled = it)
                }
        }

    }

    fun toggleLocalStorage() {
        viewModelScope.launch {
            prefs.toggleLocalStorage()
        }
    }

    fun toggleFavoritesFeature() {
        viewModelScope.launch {
            prefs.toggleFavoritesFeature()
        }
    }

    fun toggleRotation() {
        viewModelScope.launch {
            prefs.toggleRotation()
        }
    }

}