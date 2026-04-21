package com.example.newsandhistory.ui.theme.screens.newsList

import android.annotation.SuppressLint
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.newsandhistory.dataclasses.CurrentNews
import com.example.newsandhistory.newsinfo.samples.sampleNews
import com.example.newsandhistory.prefs.NewsPrefs
import com.example.newsandhistory.repositories.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@SuppressLint("UnusedCrossfadeTargetStateParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun NewsListScreen(
    viewModel: NewsListViewModel,
    onNewsRowTap: (newsIndex: Int) -> Unit,
    onSettingsTap: () -> Unit,
    onAboutTap: () -> Unit,
) {

    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.country_info_screen_title)) },
                actions = {
                    IconButton(
                        onClick = onSettingsTap,
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = stringResource(id = R.string.about_content_description),
                        )
                    }
                    IconButton(
                        onClick = onAboutTap,
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Help,
                            contentDescription = stringResource(id = R.string.about_content_description),
                        )
                    }
                }
            )
        },
    ) { padding ->
        val transition = updateTransition(
            targetState = state,
            label = "list_state_transition",
        )
        transition.Crossfade(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentKey = { it.javaClass },
        ) { state ->
            when (state) {
                is NewsListState.Loading -> NewsListState.Loading
                is NewsListState.Success -> NewsInfoList(
                    newsListState = state,
                    onRefreshTap = viewModel::fetchNews,
                    onNewsRowTap = onNewsRowTap,
                    // onNewsRowFavorite = viewModel::favorite,
                )
                is NewsListState.Error -> ErrorMessage(
                    userFriendlyMessageText = stringResource(id = news_info_error),
                    error = state.error,
                    onRetry = viewModel::fetchNews,
                )
            }
        }
    }
}


@SuppressLint("ViewModelConstructorInComposable")
@Inject
@Preview
@Composable
fun NewsInfoScreenPreview() {
    NewsListScreen(
        viewModel = NewsListViewModel(
            repository = object : NewsRepository {
                override val currentNews: Flow<CurrentNews>
                    get() = MutableStateFlow(sampleNews).asStateFlow()

                //  get() = MutableStateFlow(sampleNews).asStateFlow()
                override suspend fun fetchNews() {}

                override fun getNewsStory(index: Int): CurrentNews = sampleNews
                override suspend fun breakingNews(currentNews: CurrentNews) {}




            },
            prefs = object : NewsPrefs {
                override fun getLocalStorageEnabled(): Flow<Boolean> {
                    TODO("Not yet implemented")
                }

                override fun getFavoritesFeatureEnabled(): Flow<Boolean> {
                    TODO("Not yet implemented")
                }

                override fun getRotationEnabled(): Flow<Boolean> {
                    TODO("Not yet implemented")
                }

                override suspend fun toggleLocalStorage() {
                    TODO("Not yet implemented")
                }

                override suspend fun toggleFavoritesFeature() {
                    TODO("Not yet implemented")
                }

                override suspend fun toggleRotation() {
                    TODO("Not yet implemented")
                }


            }
        ),
        onNewsRowTap = {},
        onSettingsTap = {},
        onAboutTap = {},
    )
}

