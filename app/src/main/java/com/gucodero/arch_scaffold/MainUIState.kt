package com.gucodero.arch_scaffold

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MainUIState(
    val counter: Int = 0
): Parcelable