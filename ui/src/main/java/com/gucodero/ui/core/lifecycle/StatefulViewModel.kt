package com.gucodero.ui.core.lifecycle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.gucodero.ui.core.lifecycle.base.UIStateHolder

abstract class StatefulViewModel<UIState, UIEvent>(
    defaultUIState: () -> UIState,
    defaultUIEvent: UIEvent? = null,
    private val keySavedUIState: String = "UIState",
    private val savedStateHandler: SavedStateHandle? = null
): StatelessViewModel<UIEvent>(
    defaultUIEvent = defaultUIEvent
), UIStateHolder<UIState> {

    private val _changeUiStateLiveData = MutableLiveData<Pair<UIState?, UIState>>(
        Pair(null, savedStateHandler?.get<UIState>(keySavedUIState) ?: defaultUIState())
    )
    private val _uiStateLiveData = MutableLiveData(_changeUiStateLiveData.value!!.second)
    override val changeUiStateLiveData: LiveData<Pair<UIState?, UIState>>
        get() = _changeUiStateLiveData
    override val uiStateLiveData: LiveData<UIState>
        get() = _uiStateLiveData
    override val uiState: UIState get() = uiStateLiveData.value!!

    override fun setUIState(uiState: UIState) {
        val oldValue = this.uiState
        if (oldValue === uiState) return
        _changeUiStateLiveData.value = oldValue to uiState
        _uiStateLiveData.value = uiState
        savedStateHandler?.set(keySavedUIState, uiState)
    }

}