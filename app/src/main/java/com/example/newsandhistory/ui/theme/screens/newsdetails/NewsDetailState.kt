package com.example.newsandhistory.ui.theme.screens.newsdetails

import com.example.newsandhistory.dataclasses.CurrentNews

sealed class NewsDetailsState {

    data object Loading : NewsDetailsState()
    data class Success(val news: CurrentNews?) : NewsDetailsState()
    data class Error(val error: Throwable) : NewsDetailsState()
}