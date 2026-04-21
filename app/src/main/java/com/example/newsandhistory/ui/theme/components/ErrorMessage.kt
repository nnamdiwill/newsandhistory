package com.example.newsandhistory.ui.theme.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.R
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun ErrorMessage(
    userFriendlyMessageText: String,
    error: Throwable,
    onRetry: (() -> Unit)? = null,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Log.e("NewsErrorScreen", "Error: ${error.message}")
        Text(
            text = userFriendlyMessageText,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
        )

        onRetry?.let {
            Button(
                onClick = onRetry,
                modifier = Modifier.padding(top = 16.dp),
            ) {
                Text(text = stringResource(id = R.string.retry))
            }
        }

    }

}
@Preview
@Composable
fun ErrorMessagePreview(){
    ErrorMessage(
        userFriendlyMessageText = "Error message",
        error = Throwable("Error message"),
        onRetry = {},
    )
}