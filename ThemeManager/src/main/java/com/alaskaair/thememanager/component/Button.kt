package com.alaskaair.thememanager.component

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alaskaair.thememanager.theme.UIRepositoryTheme

/**
 * Composable function to create a filled button.
 *
 * This function creates a filled button with the provided parameters.
 * The button is enabled or disabled based on the isEnabled parameter.
 * The button's width is determined by the width parameter.
 * The button's text is determined by the text parameter.
 * The button's click action is determined by the onButtonClick parameter.
 *
 * @param modifier The modifier to be applied to the button. Default is Modifier.
 * @param isEnabled A boolean indicating whether the button is enabled. Default is true.
 * @param width The modifier to be applied to the button's width. Default is Modifier.fillMaxWidth().
 * @param text The text to be displayed on the button.
 * @param onButtonClick The action to be performed when the button is clicked. Default is an empty action.
 */
@Composable
fun FilledButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    width: Modifier = Modifier.fillMaxWidth(),
    text: String,
    onButtonClick: () -> Unit = {}
) {
    val alpha = if (isEnabled) 1f else 0.4f
    Button(elevation = ButtonDefaults.buttonElevation(
        defaultElevation = 0.dp
    ),
        enabled = isEnabled,
        onClick = {
            onButtonClick()
        },
        modifier = modifier
            .height(50.dp)
            .then(width)
            .alpha(alpha),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
        ),
        content = {
            Text(
                text = text,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                textDecoration = TextDecoration.Underline
            )
        })
}

/**
 * Composable function to create an outlined button.
 *
 * This function creates an outlined button with the provided parameters.
 * The button is enabled or disabled based on the isEnabled parameter.
 * The button's width is determined by the width parameter.
 * The button's text is determined by the text parameter.
 * The button's click action is determined by the onButtonClick parameter.
 *
 * @param modifier The modifier to be applied to the button. Default is Modifier.
 * @param isEnabled A boolean indicating whether the button is enabled. Default is true.
 * @param width The modifier to be applied to the button's width. Default is Modifier.fillMaxWidth().
 * @param text The text to be displayed on the button.
 * @param onButtonClick The action to be performed when the button is clicked. Default is an empty action.
 */
@Composable
fun OutlineButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    width: Modifier = Modifier.fillMaxWidth(),
    text: String,
    onButtonClick: () -> Unit = {}
) {
    val alpha = if (isEnabled) 1f else 0.4f
    Button(elevation = ButtonDefaults.buttonElevation(
        defaultElevation = 0.dp
    ),
        enabled = isEnabled,
        onClick = {
            onButtonClick()
        },
        modifier = modifier
            .height(50.dp)
            .then(width)
            .alpha(alpha),
        border = BorderStroke(
            2.dp,
            MaterialTheme.colorScheme.onPrimary
        ),
        shape = RoundedCornerShape(100.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
        content = {
            Text(
                text = text,
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center,
            )
        })
}

/**
 * Composable function to create a text button.
 *
 * This function creates a text button with the provided parameters.
 * The button is enabled or disabled based on the isEnabled parameter.
 * The button's width is determined by the width parameter.
 * The button's text is determined by the text parameter.
 * The button's click action is determined by the onButtonClick parameter.
 *
 * @param modifier The modifier to be applied to the button. Default is Modifier.
 * @param isEnabled A boolean indicating whether the button is enabled. Default is true.
 * @param width The modifier to be applied to the button's width. Default is Modifier.fillMaxWidth().
 * @param text The text to be displayed on the button.
 * @param onButtonClick The action to be performed when the button is clicked. Default is an empty action.
 */
@Composable
fun TextButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    width: Modifier = Modifier.fillMaxWidth(),
    text: String,
    onButtonClick: () -> Unit = {}
) {
    val alpha = if (isEnabled) 1f else 0.4f
    Button(elevation = ButtonDefaults.buttonElevation(
        defaultElevation = 0.dp
    ),
        enabled = isEnabled,
        onClick = {
            onButtonClick()
        },
        modifier = modifier
            .height(50.dp)
            .then(width)
            .alpha(alpha),
        shape = RoundedCornerShape(100.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
        content = {
            Text(
                text = text,
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center,
            )
        })
}

/**
 * Preview function for the button composables.
 *
 * This function provides a preview of the button composables in both light and dark themes.
 */
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark")
@Composable
private fun ButtonPreview() {
    UIRepositoryTheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
        ) {
            FilledButton(
                modifier = Modifier
                    .padding(32.dp),
                text = "login"
            )
            OutlineButton(
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .padding(bottom = 32.dp),
                text = "join mileage plan"
            )
            TextButton(
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .padding(bottom = 32.dp),
                text = "continue as guest"
            )
        }
    }
}