package com.gucodero.ui.compose.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.gucodero.ui.compose.fragment.AppComposeStatefulFragment
import com.gucodero.ui.core.lifecycle.StatefulViewModel
import com.gucodero.ui.core.lifecycle.StatelessViewModel
import com.gucodero.ui.core.lifecycle.base.ViewModelLoadable
import com.gucodero.ui.core.util.connectLoadingEvent
import com.gucodero.ui.core.util.notIsFragmentException
import kotlinx.coroutines.launch

@Composable
inline fun <reified S, V: StatefulViewModel<S, *>> AppComposeStatefulFragment<V>.uiState(): State<S> {
    return if (LocalInspectionMode.current) {
        uiStatePreview()
    } else {
        viewModel.uiStateLiveData.observeAsState(viewModel.uiState)
    }
}

@Composable
fun <S: Any, V: StatefulViewModel<S, *>> AppComposeStatefulFragment<V>.PreviewUiState(
    uiState: S,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalUiStatePreview provides uiState
    ) {
        content()
    }
}

@Composable
fun <E, V: StatelessViewModel<E>> AppComposeStatefulFragment<V>.OnUiEvent(
    content: (uiEvent: E) -> Unit
) {
    if (!LocalInspectionMode.current) {
        val scope = rememberCoroutineScope()
        remember {
            scope.launch {
                viewModel.setOnUiEvent(content)
            }
        }
    }
}

fun <V: ViewModel> AppComposeStatefulFragment<V>.connectLoadingEvent() {
    if (this is Fragment) {
        viewModel.let {
            if (it is ViewModelLoadable) {
                connectLoadingEvent(it)
            }
        }
    } else {
        throw notIsFragmentException
    }
}