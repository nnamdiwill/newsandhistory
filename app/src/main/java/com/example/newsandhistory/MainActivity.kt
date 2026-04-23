package com.example.newsandhistory

import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_SENSOR
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.example.newsandhistory.nav.NewsInfoNavHost
import com.example.newsandhistory.prefs.NewsPrefs
import com.example.newsandhistory.ui.theme.NewsAndHistoryTheme
import kotlinx.coroutines.launch
import javax.inject.Inject
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var prefs: NewsPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            prefs.getRotationEnabled()
                .collect { rotationEnabled ->
                    requestedOrientation = if (rotationEnabled) {
                        SCREEN_ORIENTATION_SENSOR
                    } else {
                        SCREEN_ORIENTATION_PORTRAIT
                    }
                }
        }
        setContent {
            NewsAndHistoryTheme {
                NewsInfoNavHost()
            }
        }
    }
}


// https://github.com/nnamdiwill/newsandhistory