package com.gucodero.arch_scaffold.ui.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeUIState(
    val counter: Int = 0
): Parcelable