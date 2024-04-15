package com.alaskaair.uirepository.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.alaskaair.uirepository.R
import com.alaskaair.uirepository.ui.main.presentation.NewMainActivity
import com.alaskaair.uirepository.ui.theme.BrandColor
import com.alaskaair.uirepository.ui.theme.UIRepositoryTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

/**
 * SplashActivity is the first screen that is shown when the app is launched.
 * It displays the app logo with a fade-in animation, then navigates to the main activity.
 */
@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : ComponentActivity() {

    /**
     * This is the first method that gets called when this activity is launched.
     * It sets the content view to the SplashScreen composable.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UIRepositoryTheme {
                SplashScreen()
            }
        }
    }

    /**
     * This is a preview function for the SplashScreen composable.
     * It's annotated with @Preview so it can be previewed in Android Studio's design tools.
     */
    @Preview
    @Composable
    private fun SplashScreen() {
        // This is an animatable data holder that starts with an initial value of 0f (fully transparent).
        val alpha = remember {
            Animatable(0f)
        }

        // This launched effect block is where the animation and navigation logic happens.
        LaunchedEffect(key1 = true, block = {
            // Animate the alpha value to 1f (fully opaque) over 1500 milliseconds.
            alpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(1500)
            )
            // Wait for 2000 milliseconds before navigating to the main activity.
            delay(2000)
            // Start the main activity.
            startActivity(Intent(this@SplashActivity, NewMainActivity::class.java))
            // Finish this activity along with all activities in the task that launched it.
            finishAffinity()
        })
        // This is the main layout of the SplashScreen.
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BrandColor), contentAlignment = Alignment.Center
        ) {
            // This is the app logo image.
            Image(
                modifier = Modifier.alpha(alpha.value),
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "App Logo"
            )
        }
    }

}