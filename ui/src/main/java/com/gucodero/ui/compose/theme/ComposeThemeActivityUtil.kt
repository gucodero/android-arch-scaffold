package com.gucodero.ui.compose.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalInspectionMode

@Composable
internal fun GlobalTheme(content: @Composable () -> Unit) {
    val isPreview = LocalInspectionMode.current
    if (isPreview) {
        content()
    } else {
        GlobalThemeProvider.Theme {
            content()
        }
    }

}