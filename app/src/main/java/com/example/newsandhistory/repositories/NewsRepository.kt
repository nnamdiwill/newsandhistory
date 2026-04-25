package com.example.newsandhistory.repositories

import com.example.newsandhistory.dataclasses.CurrentNews
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    val currentNews: Flow<List<CurrentNews>>
    suspend fun  fetchNews()
    fun getNewsStory(index: Int): CurrentNews?
    suspend fun breakingNews(currentNews: CurrentNews)
    // fun fetchCurrentNews()
}
