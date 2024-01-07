package com.gucodero.arch_scaffold

import androidx.lifecycle.SavedStateHandle
import com.gucodero.domain.counter.CounterUC
import com.gucodero.ui.core.lifecycle.StatefulViewModel
import com.gucodero.ui.core.util.launch
import com.gucodero.ui.core.util.setUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    savedStateHandler: SavedStateHandle,
    private val counterUC: CounterUC
): StatefulViewModel<MainUIState, MainUIEvent>(
    defaultUIState = {
        MainUIState()
    },
    savedStateHandler = savedStateHandler
) {

    fun onIncrement() = launch {
        executeCounterUC(CounterUC.Params.Increment(uiState.counter))
    }

    fun onDecrement() = launch {
        executeCounterUC(CounterUC.Params.Decrement(uiState.counter))
    }

    private suspend inline fun executeCounterUC(params: CounterUC.Params) {
        when(val result = counterUC(params)) {
            is CounterUC.Result.Success -> {
                setUIState {
                    copy(
                        counter = result.value
                    )
                }
            }
            is CounterUC.Result.DecrementNotValid -> {
                MainUIEvent.DecrementNotValid.send()
            }
        }
    }
}