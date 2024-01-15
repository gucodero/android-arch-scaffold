package com.gucodero.ui.compose.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.ViewModel
import com.gucodero.ui.compose.util.connectLoadingEvent
import com.gucodero.ui.core.fragment.AppDialog
import com.gucodero.ui.core.util.appViewModels
import kotlin.reflect.KClass

sealed class AppComposeDialog(
    isCancelable: Boolean = true
): AppDialog(isCancelable) {

    open fun onInit() {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        onInit()
        return ComposeView(requireContext()).apply {
            setContent {
                Screen()
            }
        }
    }

    @Composable
    abstract fun Screen()

    @Composable
    abstract fun ScreenPreview()

    abstract class Stateless(
        isCancelable: Boolean = true
    ): AppComposeDialog(isCancelable)

    abstract class Stateful<V: ViewModel>(
        override val clazz: KClass<V>,
        override val storeOwner: Int? = null,
        isCancelable: Boolean = true
    ): AppComposeDialog(isCancelable), AppComposeStatefulFragment<V> {

        override val viewModel: V by appViewModels()

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            connectLoadingEvent()
        }
    }

}