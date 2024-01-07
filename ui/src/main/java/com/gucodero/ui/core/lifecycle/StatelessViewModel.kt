package com.gucodero.ui.core.lifecycle

import androidx.lifecycle.ViewModel
import com.gucodero.ui.core.lifecycle.base.UIEventHolder
import com.gucodero.ui.core.lifecycle.base.ViewModelLoadable
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow

abstract class StatelessViewModel<UIEvent>(
    defaultUIEvent: UIEvent? = null
): ViewModel(), UIEventHolder<UIEvent>, ViewModelLoadable {

    private val eventChannel = Channel<UIEvent>(Channel.BUFFERED)
    private val _isLoading = MutableStateFlow(false)
    override var isLoading: Boolean
        get() = _isLoading.value
        set(value) {
            _isLoading.tryEmit(value)
        }

    override suspend fun setOnUiEvent(onEvent: suspend (UIEvent) -> Unit) {
        eventChannel.receiveAsFlow().collectLatest(onEvent)
    }

    final override fun UIEvent.send() {
        eventChannel.trySendBlocking(this)
    }

    override suspend fun onLoading(listener: (isLoading: Boolean) -> Unit) {
        _isLoading.collectLatest {
            listener(it)
        }
    }

    init {
        defaultUIEvent?.send()
    }

}