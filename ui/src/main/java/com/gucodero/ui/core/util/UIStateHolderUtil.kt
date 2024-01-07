package com.gucodero.ui.core.util

import androidx.appcompat.app.AppCompatActivity
import com.gucodero.ui.core.lifecycle.StatefulViewModel
import com.gucodero.ui.core.lifecycle.base.UIStateHolder

inline fun <UIState> UIStateHolder<UIState>.setUIState(block: UIState.() -> UIState) {
    setUIState(uiState.block())
}

fun <UiState> AppCompatActivity.onUIState(
    viewModel: StatefulViewModel<UiState, *>,
    listenWhen: ((previous: UiState?, current: UiState) -> Boolean)? = null,
    block: (UiState) -> Unit
){
    viewModel.changeUiStateLiveData.observe(this) {
        it.onUIState(
            listenWhen = listenWhen,
            block = block
        )
    }
}

fun <U> Pair<U?, U>?.onUIState(
    listenWhen: ((previous: U?, current: U) -> Boolean)? = null,
    block: (U) -> Unit
){
    if(this == null){
        return
    }
    if (listenWhen?.invoke(first, second) != false) {
        block(second)
    }
}