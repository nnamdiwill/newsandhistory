package com.example.newsandhistory.ui.theme.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.newsandhistory.ui.theme.screens.newsList.NewsListState


@Composable
fun NewsInfoList(

    newsListState: NewsListState.Success,
    onRefreshTap: () -> Unit,
    onNewsRowTap: (newsIndex: Int) -> Unit,


    ) {


    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(onClick = onRefreshTap) {
                //  Text(text = stringResource(id = R.string.news_info_refresh_button_text))
                Text(text = stringResource(id = R.string.news_info_refresh_button_text))
            }
        }

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            itemsIndexed(newsListState.news) { index, currentNews ->
                NewsInfoRow(
                    currentNews = currentNews,
                    onTap = {
                        onNewsRowTap(index)
                    },
                    onFavorite = TODO()
                )
            }


        }
    }

}

