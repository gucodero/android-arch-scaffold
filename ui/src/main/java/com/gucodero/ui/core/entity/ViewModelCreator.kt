package com.gucodero.ui.core.entity

import androidx.lifecycle.ViewModel
import kotlin.reflect.KClass

data class ViewModelCreator<T: ViewModel>(
    val clazz: KClass<T>,
    val store: Int? = null
)