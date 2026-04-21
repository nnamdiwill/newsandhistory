package com.example.newsandhistory.ui.theme.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsandhistory.dataclasses.CurrentNews
import com.example.newsandhistory.samples.sampleNews


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsInfoRow(
    currentNews : CurrentNews,
    onTap: () -> Unit,
    onFavorite: () -> Unit,
){


    Card(
        onClick = onTap,
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().height(64.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.padding(all = 8.dp)) {
                Text(text = "Name: $")
                Text(text = "Capital: $")
            }

        }
    }
}

@Preview
@Composable
fun NewsInfoRowPreview() {
    NewsInfoRow(
        currentNews = sampleNews,
        onTap = {},
        onFavorite = {},
    )
}