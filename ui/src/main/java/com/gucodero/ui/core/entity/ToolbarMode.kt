package com.gucodero.ui.core.entity

import androidx.annotation.StringRes

sealed class ToolbarMode {
    data class Visible(
        @StringRes
        val titleId: Int,
        val allowBack: Boolean = false
    ) : ToolbarMode()

    data object Hide : ToolbarMode()

    data object None : ToolbarMode()
}