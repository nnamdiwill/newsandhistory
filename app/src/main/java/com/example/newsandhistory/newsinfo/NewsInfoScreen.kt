package com.example.newsandhistory.newsinfo

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.newsandhistory.dataclasses.CurrentNews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsInfoScreen(

    viewModel: NewsInfoViewModel,
    onCurrentNewsRowTap: (CurrentNews) -> Unit,
    onRefreshTap: () -> Unit,

    onCurrentNewsRowFavorite: (currentNews:  CurrentNews) -> Unit,

    ) {
    val state = viewModel.uiState.collectAsState()

    Scaffold( topBar = {
        TopAppBar(
            title = { Atext(text = stringResource(id = R.string.country_info_screen_title)) },
            actions = {
                AIconButton (
                    modifier = Modifier,
                    onClick = onCurrentNewsRowTap,
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Help,
                        contentDescription = stringResource(id = R.string.about_content_description),
                    )
                }
            }
        )
    },
    ){ padding ->
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
                is NewsInfoState.Loading -> NewsInfoState.Loading()
                is NewsInfoState.Success -> NewsInfoList(
                    news = state.newsinfolinks,
                    onRefreshTap = viewModel::NewsDetailViewModel,
                    onNewsRowTap = onCurrentNewsRowTap,
                    onNewsRowFavorite = viewModel::favorite,
                )
                is NewsInfoState.Error -> Error(
                    userFriendlyMessageText = stringResource(id = R.string.country_info_error),
                    error = state.error,
                    onRetry = viewModel::NewsInfoViewModel
                )
            }
        }
    }
}
