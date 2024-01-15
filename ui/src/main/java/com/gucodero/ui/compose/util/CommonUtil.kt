package com.gucodero.ui.compose.util

import androidx.activity.ComponentDialog
import androidx.activity.OnBackPressedCallback
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.State
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog

val LocalUiStateException = ExceptionInInitializerError("use PreviewUiState")

val LocalUiStatePreview: ProvidableCompositionLocal<Any> = compositionLocalOf {
    throw LocalUiStateException
}

@Composable
inline fun <reified S> uiStatePreview(): State<S> {
    val uiState = LocalUiStatePreview.current
    return if (uiState is S) {
        mutableStateOf(uiState)
    } else {
        throw LocalUiStateException
    }
}

@Composable
fun Fragment.OnBackPressed(
    block: () -> Unit
) {
    if (!LocalInspectionMode.current) {
        val fragment = this
        remember {
            val callback = object : OnBackPressedCallback(false) {
                override fun handleOnBackPressed() {
                    isEnabled = false
                    block()
                    isEnabled = true
                }
            }
            when(fragment) {
                is DialogFragment -> when(val dialog = fragment.dialog) {
                    is BottomSheetDialog -> {
                        dialog.onBackPressedDispatcher
                            .addCallback(fragment, callback)
                    }
                    is ComponentDialog -> {
                        dialog.onBackPressedDispatcher
                            .addCallback(fragment, callback)
                    }
                    else -> Unit
                }
                else -> {
                    fragment.requireActivity().onBackPressedDispatcher
                        .addCallback(fragment, callback)
                }
            }
            callback.apply {
                isEnabled = true
            }
        }
    }
}