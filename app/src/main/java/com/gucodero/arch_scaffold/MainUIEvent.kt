package com.gucodero.arch_scaffold

sealed class MainUIEvent {
    data object DecrementNotValid: MainUIEvent()

    data class People(val data: String): MainUIEvent()

}