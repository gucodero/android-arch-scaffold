package com.gucodero.ui.core.lifecycle.base

import androidx.lifecycle.LiveData

interface UIStateHolder<UIState> {

    val uiState: UIState
    val uiStateLiveData: LiveData<UIState>
    val changeUiStateLiveData: LiveData<Pair<UIState?, UIState>>
    fun setUIState(uiState: UIState)
}