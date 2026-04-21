package com.example.newsandhistory.newsinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class NewsInfoViewModel(
    private val repository: NewsRepository
) : ViewModel()  {

    // val news: CurrentNews? = null
    private val _uptimeCounter = MutableStateFlow(0)
    private val _uiState = MutableStateFlow<NewsInfoState>(NewsInfoState.Loading(_uptimeCounter.value))

    val uiState: StateFlow<NewsInfoState> = _uiState

    init {
        fetchNews()
        startUptimeCounter()
    }

    private fun startUptimeCounter(){
        viewModelScope.launch {
            while(true){
                delay(1_000L)
                _uptimeCounter.value++
                if (_uiState.value is NewsInfoState.Loading){
                    _uiState.value = NewsInfoState.Loading(_uptimeCounter.value)
                }
            }
        }
    }
    class NewsInfoViewModelFactory(
        private val repository: NewsRepository) :
        ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            NewsInfoViewModel(repository) as T
    }



    fun fetchNews() {
        viewModelScope.launch {
            _uiState.value = NewsInfoState.Loading(_uptimeCounter.value)

            // Added artificial delay on purpose
            // to view the loading screen a bit longer.
            delay(1_000L)

            repository
                .currentNews
                .catch {
                    _uiState.value = NewsInfoState.Error(it)
                }
                .collect {
                    _uiState.value = NewsInfoState.Success(it)
                }
        }
    }


}