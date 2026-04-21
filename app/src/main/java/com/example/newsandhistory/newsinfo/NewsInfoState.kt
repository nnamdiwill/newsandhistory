package com.example.newsandhistory.newsinfo

import com.example.newsandhistory.dataclasses.CurrentNews

sealed class NewsInfoState {
    // updated class files
    data class Loading() : NewsInfoState()
    data class Success(val newsLinks: List<CurrentNews>) : NewsInfoState()
    data class Error(val error: Throwable,val userFriendlyMessageText: String) :NewsInfoState()



}