package com.example.newsandhistory.ui.theme.screens.newsList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsandhistory.prefs.NewsPrefs
import com.example.newsandhistory.repositories.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


class NewsListViewModel @Inject constructor(
    private val repository: NewsRepository,
    private val prefs: NewsPrefs,
) : ViewModel() {

    private var isFavoritesFeatureEnabled = false
        set(value) {
            field = value
            val uiStateValue = _uiState.value
            if (uiStateValue is NewsListState.Success) {
                _uiState.value = uiStateValue.copy(isFavoritesFeatureEnabled = value)
            }
        }

    private val _uiState = MutableStateFlow<NewsListState>(NewsListState.Loading)
    val uiState: StateFlow<NewsListState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            prefs.getFavoritesFeatureEnabled()
                .collect { favoritesFeatureEnabled ->
                    isFavoritesFeatureEnabled = favoritesFeatureEnabled
                }
        }

        viewModelScope.launch {
            repository
                .currentNews
                .catch {
                    _uiState.value = NewsListState.Error(it)
                }
                .collect {
                    _uiState.value = NewsListState.Success(
                        news = it,
                        isFavoritesFeatureEnabled = isFavoritesFeatureEnabled,
                    )
                }
        }

        fetchNews()
    }

    fun fetchNews() {
        _uiState.value = NewsListState.Loading

        viewModelScope.launch {
            try {
                repository.fetchNews()
            } catch (e: Exception) {
                _uiState.value = NewsListState.Error(e)
            }

        }

    }
}
