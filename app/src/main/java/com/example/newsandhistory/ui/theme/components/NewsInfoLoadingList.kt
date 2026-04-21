package com.example.newsandhistory.ui.theme.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.newsandhistory.ui.theme.animations.shimmer


@Composable
fun NewsInfoLoadingList() {

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(
                onClick = {},
                modifier = Modifier.shimmer(),
            ) {
                Text(text = "       ")
            }
        }
    }

    Column(modifier = Modifier.fillMaxWidth()) {

        NewsInfoLoadingRow()
        NewsInfoLoadingRow()
        NewsInfoLoadingRow()

    }

}

@Preview
@Composable
fun NewsInfoLoadingListPreview() {
    NewsInfoLoadingList()
}