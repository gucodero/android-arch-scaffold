package com.gucodero.ui.core.util

import androidx.annotation.IdRes
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.google.android.material.R
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.properties.ReadOnlyProperty

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

fun <T: DialogFragment> T.isCancelable(isCancelable: Boolean): T {
    this.isCancelable = isCancelable
    return this
}

fun <T: DialogFragment> T.fullscreen(): T {
    setStyle(
        DialogFragment.STYLE_NORMAL,
        R.style.ShapeAppearanceOverlay_MaterialComponents_MaterialCalendar_Window_Fullscreen
    )
    return this
}

fun <T: Fragment> T.putArgs(vararg pairs: Pair<String, Any?>): T {
    arguments = bundleOf(*pairs)
    return this
}

fun <T> argument(
    key: String,
    defaultValue: T? = null
): ReadOnlyProperty<Fragment, T> {
    return ReadOnlyProperty { thisRef, _ ->
        @Suppress("UNCHECKED_CAST")
        (thisRef.arguments?.get(key) ?: defaultValue) as T
    }
}