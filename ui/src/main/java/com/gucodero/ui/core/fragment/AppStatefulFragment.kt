package com.gucodero.ui.core.fragment

import androidx.lifecycle.ViewModel
import kotlin.reflect.KClass

interface AppStatefulFragment<V: ViewModel> {
    val clazz: KClass<V>
    val storeOwner: Int?
}