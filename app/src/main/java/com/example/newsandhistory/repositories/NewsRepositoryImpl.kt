package com.example.newsandhistory.repositories

import com.example.newsandhistory.NewsService
import com.example.newsandhistory.databases.NewsDAO
import com.example.newsandhistory.dataclasses.CurrentNews
import com.example.newsandhistory.prefs.NewsPrefs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class NewsRepositoryImpl(
    private val service: NewsService,
    private val dao: NewsDAO,
    private val prefs: NewsPrefs,
) :NewsRepository {
    // private var favorites = setOf<String>()
    private var updates = setOf<String>()
    private val _news: MutableStateFlow<List<CurrentNews>> = MutableStateFlow(emptyList())

    override val currentNews: StateFlow<List<CurrentNews>> = _news.asStateFlow()


    override suspend fun fetchNews() {

        val newsResponse = service.getCurrentNews()

        _news.value = emptyList()
        _news.value = try {
            if (newsResponse.isSuccessful) {
                newsResponse.body()!!
                    .toMutableList()
                    .map { currentNews ->
                        currentNews.copy(isUpdated = updates.contains(currentNews.news))
                    }
            } else {
                throw Throwable("Request failed: ${newsResponse.message()}")
            }
        } catch (e: Exception) {
            throw Throwable("Request failed: ${e.message}")
        }

    }

    override fun getNewsStory(index: Int): CurrentNews? =
        _news.value.getOrNull(index)

    override suspend fun breakingNews(news: CurrentNews) {
        updates = if (updates.contains(news.newsHeadline)) {
            updates - news.description
        } else {
            updates + news.description
        }
        val index = _news.value.indexOf(news)
        val mutableNews = _news.value.toMutableList()
        mutableNews[index] = mutableNews[index].copy(isUpdated = updates.contains(news.newsHeadline))
        _news.value = mutableNews.toList()
    }

}
