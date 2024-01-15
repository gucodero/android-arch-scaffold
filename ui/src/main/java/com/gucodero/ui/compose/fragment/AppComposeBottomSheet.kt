package com.gucodero.ui.compose.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import kotlin.reflect.KClass
import androidx.lifecycle.ViewModel
import com.gucodero.ui.compose.util.connectLoadingEvent
import com.gucodero.ui.core.fragment.AppBottomSheet
import com.gucodero.ui.core.util.appViewModels

sealed class AppComposeBottomSheet(
    isCancelable: Boolean = true
): AppBottomSheet(isCancelable) {

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
    ): AppComposeBottomSheet(isCancelable)

    abstract class Stateful<V: ViewModel>(
        override val clazz: KClass<V>,
        override val storeOwner: Int? = null,
        isCancelable: Boolean = true
    ): AppComposeBottomSheet(isCancelable), AppComposeStatefulFragment<V> {

        override val viewModel: V by appViewModels()

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            connectLoadingEvent()
        }
    }

}