package com.qamar.mycontacts.core.presentaion

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.qamar.mycontacts.ui.theme.DarkColors
import com.qamar.mycontacts.ui.theme.LightColors
import com.qamar.mycontacts.ui.theme.Typography

@Composable
actual fun ContactsTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if(darkTheme) DarkColors else LightColors,
        typography = Typography,
        content = content
    )
}