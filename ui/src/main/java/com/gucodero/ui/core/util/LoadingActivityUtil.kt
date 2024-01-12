package com.gucodero.ui.core.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.gucodero.ui.core.activity.LoadingActivity
import com.gucodero.ui.core.lifecycle.base.ViewModelLoadable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal fun Fragment.requireLoadingActivity(): LoadingActivity {
    val result = requireActivity()
    return if (result is LoadingActivity) {
        result
    } else {
        throw ClassCastException("Activity does not implement LoadingActivity.")
    }
}

suspend fun Fragment.showLoading(timeMillis: Long) {
    isLoading = true
    delay(timeMillis)
    isLoading = false
}

var Fragment.isLoading: Boolean
    get() = requireLoadingActivity().isLoading
    set(value) {
        if (value && !isLoading) {
            requireLoadingActivity().isLoading = true
        } else if (!value && isLoading) {
            requireLoadingActivity().isLoading = false
        }
    }

fun Fragment.connectLoadingEvent(viewModel: ViewModelLoadable){
    viewLifecycleOwner.lifecycleScope.launch {
        try {
            viewModel.onLoading {
                isLoading = it
            }
        } finally {
            if(!viewModel.isLoading){
                isLoading = false
            }
        }
    }
}