package com.gucodero.ui.core.util

import androidx.lifecycle.ViewModel
import com.gucodero.ui.core.entity.ViewModelCreator
import com.gucodero.ui.core.fragment.StatefulFragment
import kotlin.reflect.KClass

fun <T: ViewModel> viewModelCreator(
    clazz: KClass<T>,
    store: Int? = null
): ViewModelCreator<T> {
    return ViewModelCreator(
        clazz = clazz,
        store = store
    )
}

inline fun <reified T: ViewModel> StatefulFragment<T>.viewModelCreator(
    store: Int? = null
) = viewModelCreator(
    clazz = T::class,
    store = store
)