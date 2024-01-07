package com.gucodero.ui.core.util

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.gucodero.ui.core.lifecycle.base.UIEventHolder
import kotlinx.coroutines.launch

fun <UIEvent> AppCompatActivity.onUIEvent(
    viewModel: UIEventHolder<UIEvent>,
    block: suspend (UIEvent) -> Unit
) {
    lifecycleScope.launch {
        viewModel.setOnUiEvent(block)
    }
}