package com.alaskaair.uirepository.ui.main.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.alaskaair.uirepository.R
import com.alaskaair.uirepository.common.State
import com.alaskaair.uirepository.data.entity.DesignToken
import com.alaskaair.uirepository.ui.component.FilledButton
import com.alaskaair.uirepository.ui.main.viewmodel.DesignTokenViewModel
import com.alaskaair.uirepository.ui.theme.UIRepositoryTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.alaskaair.thememanager.component.FilledButton as FilledButtonFromThemeManager
import com.alaskaair.thememanager.component.OutlineButton as OutlineButtonFromThemeManager
import com.alaskaair.thememanager.component.TextButton as TextButtonFromThemeManager
import com.alaskaair.thememanager.data.DesignToken as DesignTokenForThemeManager
import com.alaskaair.thememanager.theme.UIRepositoryTheme as UIRepositoryThemeForThemeManager

/**
 * Main activity of the application.
 *
 * This activity is responsible for fetching design tokens and setting the content of the application.
 * It uses the [DesignTokenViewModel] to fetch the design tokens.
 *
 * @property designTokenVM The ViewModel used to fetch design tokens.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // ViewModel for fetching design tokens
    private val designTokenVM: DesignTokenViewModel by viewModels()

    // Mutable state for tracking whether the dark theme is enabled
    private var isDarkTheme = mutableStateOf(false)

    /**
     * Called when the activity is starting.
     *
     * This method fetches the design token when the activity is created.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Launch a coroutine in the lifecycle scope
        lifecycleScope.launch {
            // Repeat the action of fetching design tokens whenever the lifecycle state is at least CREATED
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                getDesignToken()
            }
        }
    }

    /**
     * Fetches the design token and sets the content of the application.
     *
     * This method fetches the design token from the ViewModel and sets the content of the application based on the fetched design token.
     * If the fetching fails, it sets the content with null design token enabling the fallback handling with default colors.
     */
    private suspend fun getDesignToken() {
        designTokenVM.getDesignToken().collect {
            when (it) {
                is State.LoadingState -> {
                    Log.d("MainActivity", "Loading")
                }

                is State.DataState -> {
                    if (it.data.isSuccessful) {
                        Log.d("MainActivity", "Data: ${it.data.body()}")
                        setContentUsingLocalThemeManager(
                            designToken = it.data.body()?.getDesignToken()
                        )
//                        setContentUsingThemeManagerPackage(
//                            designToken = it.data.body()?.getDesignTokenForThemeManager()
//                        )
                    } else {
                        Log.d("MainActivity", "Data: ${it.data.errorBody()}")
                        setContentUsingLocalThemeManager(designToken = null)
//                        setContentUsingThemeManagerPackage(designToken = null)
                    }
                }

                is State.ErrorState -> {
                    setContentUsingLocalThemeManager(designToken = null)
//                    setContentUsingThemeManagerPackage(designToken = null)
                }
            }
        }
    }

    /**
     * Sets the content of the application using the local theme manager.
     *
     * This method sets the content of the application using the local theme manager.
     * It uses the provided design token to set the theme of the application.
     *
     * @param designToken The design token used to set the theme of the application.
     */
    private fun setContentUsingLocalThemeManager(designToken: DesignToken?) {
        setContent {
            UIRepositoryTheme(darkTheme = isDarkTheme.value, designToken = designToken) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.primary
                ) {
                    Column(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.primary)
                    ) {
                        // IconButton for toggling between light and dark themes
                        IconButton(
                            onClick = { isDarkTheme.value = !isDarkTheme.value },
                            modifier = Modifier
                                .padding(16.dp)
                                .align(Alignment.End)
                        ) {
                            Icon(
                                painter = painterResource(id = if (isDarkTheme.value) R.drawable.dark_mode else R.drawable.light_mode),
                                contentDescription = if (isDarkTheme.value) "Dark Theme" else "Light Theme"
                            )
                        }

                        // FilledButton for login
                        FilledButton(
                            modifier = Modifier
                                .padding(32.dp),
                            text = "login"
                        )
                    }
                }
            }
        }
    }

    /**
     * Sets the content of the application using the theme manager package.
     *
     * This method sets the content of the application using the theme manager package.
     * It uses the provided design token to set the theme of the application.
     *
     * @param designToken The design token used to set the theme of the application.
     */
    private fun setContentUsingThemeManagerPackage(designToken: DesignTokenForThemeManager?) {
        setContent {
            UIRepositoryThemeForThemeManager(
                darkTheme = isDarkTheme.value,
                designToken = designToken
            ) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.primary
                ) {
                    Column(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.primary)
                    ) {
                        // IconButton for toggling between light and dark themes
                        IconButton(
                            onClick = { isDarkTheme.value = !isDarkTheme.value },
                            modifier = Modifier
                                .padding(16.dp)
                                .align(Alignment.End)
                        ) {
                            Icon(
                                painter = painterResource(id = if (isDarkTheme.value) R.drawable.dark_mode else R.drawable.light_mode),
                                contentDescription = if (isDarkTheme.value) "Dark Theme" else "Light Theme"
                            )
                        }

                        // FilledButton for login
                        FilledButtonFromThemeManager(
                            modifier = Modifier
                                .padding(32.dp),
                            text = "login"
                        )
                        // OutlineButton for joining mileage plan
                        OutlineButtonFromThemeManager(
                            modifier = Modifier
                                .padding(horizontal = 32.dp)
                                .padding(bottom = 32.dp),
                            text = "join mileage plan"
                        )
                        // TextButton for continuing as guest
                        TextButtonFromThemeManager(
                            modifier = Modifier
                                .padding(horizontal = 32.dp)
                                .padding(bottom = 32.dp),
                            text = "continue as guest"
                        )
                    }
                }
            }
        }
    }
}