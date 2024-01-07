package com.gucodero.ui.core.lifecycle.base

interface UIEventHolder<UIEvent> {

    suspend fun setOnUiEvent(onEvent: suspend (UIEvent) -> Unit)

    fun UIEvent.send()

}