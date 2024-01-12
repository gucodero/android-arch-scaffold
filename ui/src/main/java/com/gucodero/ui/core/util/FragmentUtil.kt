package com.gucodero.ui.core.util

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun Fragment.navigate(directions: NavDirections) {
    repeatOnResumed {
        try {
            findNavController().navigate(directions)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}

fun Fragment.navigate(@IdRes resId: Int) {
    repeatOnResumed {
        try {
            findNavController().navigate(resId)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}

fun Fragment.popBackStack() {
    repeatOnResumed {
        try {
            findNavController().popBackStack()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}

fun Fragment.popBackStack(@IdRes destinationId: Int, inclusive: Boolean = false) {
    repeatOnResumed {
        try {
            findNavController().popBackStack(destinationId, inclusive)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}

fun Fragment.repeatOnResumed(block: suspend CoroutineScope.() -> Unit) {
    lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            block()
        }
    }
}

fun Fragment.launch(
    context: CoroutineContext = EmptyCoroutineContext,
    block: suspend CoroutineScope.() -> Unit
) {
    lifecycleScope.launch(
        block = block,
        context = context
    )
}