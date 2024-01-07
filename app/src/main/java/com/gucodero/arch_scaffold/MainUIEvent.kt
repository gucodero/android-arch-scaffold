package com.gucodero.arch_scaffold

sealed class MainUIEvent {
    data object DecrementNotValid: MainUIEvent()

}