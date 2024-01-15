package com.gucodero.arch_scaffold.ui.home

import androidx.lifecycle.SavedStateHandle
import com.gucodero.data.common.util.safeApiCall
import com.gucodero.data.people.PeopleApi
import com.gucodero.domain.common.util.ifSuccessful
import com.gucodero.domain.counter.CounterUC
import com.gucodero.ui.core.lifecycle.StatefulViewModel
import com.gucodero.ui.core.util.launch
import com.gucodero.ui.core.util.setUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    //savedStateHandler: SavedStateHandle,
    private val counterUC: CounterUC,
    private val peopleApi: PeopleApi
): StatefulViewModel<HomeUIState, HomeUIEvent>(
    defaultUIState = {
        HomeUIState()
    },
    //savedStateHandler = savedStateHandler
) {

    fun getNewPeople() = launch {
        val result = safeApiCall {
            peopleApi.getPerson()
        }
        result.ifSuccessful {
            HomeUIEvent.People(
                data = it.toString()
            ).send()
        }
    }

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
                HomeUIEvent.DecrementNotValid.send()
            }
        }
    }
}