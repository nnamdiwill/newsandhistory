package com.example.newsandhistory.ui.theme.screens.settings

import androidx.compose.foundation.layout.Column
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
import com.example.newsandhistory.ui.theme.components.SettingsToggleRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(

    viewModel: SettingsViewModel,
    onNavigateUp: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.settings_screen_title))
                },
                navigationIcon = {
                    IconButton(onClick = {
                        onNavigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.nav_back_content_description),
                        )
                    }
                }
            )
        },
    )
    { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            SettingsToggleRow(
                label = stringResource(id = R.string.settings_enable_local_storage),
                isToggleChecked = uiState.localStorageEnabled,
                onToggleChanged = { viewModel.toggleLocalStorage() },
            )
            SettingsToggleRow(
                label = stringResource(id = R.string.settings_enable_favorites_feature),
                isToggleChecked = uiState.favoritesFeatureEnabled,
                onToggleChanged = { viewModel.toggleFavoritesFeature() },
            )
            SettingsToggleRow(
                label = stringResource(id = R.string.settings_enable_rotation),
                isToggleChecked = uiState.rotationEnabled,
                onToggleChanged = { viewModel.toggleRotation() },
            )
        }
    }
}
