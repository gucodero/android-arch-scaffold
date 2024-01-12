package com.gucodero.ui.core.entity

sealed class BottomNavMode {
    data object Visible: BottomNavMode()
    data object Hide: BottomNavMode()
    data object None: BottomNavMode()
}