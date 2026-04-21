package com.example.newsandhistory.ui.theme.screens.newsList

import com.example.newsandhistory.dataclasses.CurrentNews


sealed class NewsListState{
    data object Loading : NewsListState()
    data class Success(
        val news: CurrentNews,
        val isFavoritesFeatureEnabled: Boolean
    ) : NewsListState()
    data class Error(val error: Throwable) : NewsListState()

}