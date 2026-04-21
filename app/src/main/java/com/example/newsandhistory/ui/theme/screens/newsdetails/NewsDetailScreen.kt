package com.example.newsandhistory.ui.theme.screens.newsdetails

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.volley.toolbox.ImageRequest
import com.example.newsandhistory.repositories.NewsRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailScreen(
    newsIndex:Int,
    viewModel: NewsDetailViewModel,
    onNavigateUp: () -> Unit,
) {
    val state = viewModel.uiState.collectAsState()
    Scaffold(
        topBar = {

            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text = "${viewModel.news}",
                        textAlign = TextAlign.Center
                    )

                },
                navigationIcon = {
                    IconButton(onClick = {
                        onNavigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }

                }
            )

        },
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            item { Text(text = "news author:${viewModel.news?.author}") }
            item { Text(text = "news description:${viewModel.news!!.description}") }

            item {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(viewModel.news!!.newsHeadline)
                        .crossfade(true)
                        .build(),
                    contentDescription = "current news",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.border(1.dp, color = MaterialTheme.colorScheme.primary)
                )
            }
        }
    }
}

@SuppressLint("SuspiciousIndentation")
@Preview
@Composable
fun NewsDetailPreview(){
    val repo: NewsRepository? = null
    NewsDetailScreen(
        newsIndex = 1,
        viewModel = viewModel(

            factory = repo?.let {
                NewsDetailViewModel.NewsDetailsViewModelFactory(
                    newsId  = 1,
                    repository = it,
                )
            },
        ),
        onNavigateUp = {  }
    )



}