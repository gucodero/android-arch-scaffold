package com.gucodero.arch_scaffold.ui.home

sealed class HomeUIEvent {
    data object DecrementNotValid: HomeUIEvent()

    data class People(val data: String): HomeUIEvent()

}