package com.gucodero.ui.core.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gucodero.ui.core.fragment.AppStatefulFragment
import kotlin.reflect.KClass

val notIsFragmentException = Exception("this not is fragment")

private class AppViewModelsLazy<V: ViewModel>(
    private val fragment: Fragment,
    private val storeOwner: Int?,
    private val clazz: KClass<V>
): Lazy<V> {

    private var _value: V? = null

    override val value: V
        get() {
            if (!isInitialized()) {
                _value = if (storeOwner != null) {
                    val owner = fragment.findNavController().getViewModelStoreOwner(storeOwner)
                    ViewModelProvider(
                        owner,
                        fragment.defaultViewModelProviderFactory
                    )[clazz.java]
                } else {
                    ViewModelProvider(fragment)[clazz.java]
                }
            }
            return _value!!
        }

    override fun isInitialized(): Boolean = _value != null

}

fun <V: ViewModel> AppStatefulFragment<V>.appViewModels(): Lazy<V> {
    if (this is Fragment) {
        return AppViewModelsLazy(
            fragment = this,
            storeOwner = storeOwner,
            clazz = clazz
        )
    } else {
        throw notIsFragmentException
    }
}