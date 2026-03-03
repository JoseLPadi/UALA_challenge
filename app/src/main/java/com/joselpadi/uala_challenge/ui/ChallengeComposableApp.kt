package com.joselpadi.uala_challenge.ui

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import com.joselpadi.uala_challenge.ui.navigation.NavGraphComposable
import com.joselpadi.uala_challenge.ui.theme.UALA_challengeTheme


@Composable
fun ChallengeApplicationScreen(){
    UALA_challengeTheme {
        val isPortrait = LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT
        ChallengeApplicationContent(isPortrait)
    }
}
@Composable
private fun ChallengeApplicationContent(isPortrait: Boolean) {
    NavGraphComposable(isPortrait = isPortrait)
}
