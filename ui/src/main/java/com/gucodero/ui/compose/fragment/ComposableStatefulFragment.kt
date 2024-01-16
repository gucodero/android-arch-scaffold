package com.gucodero.ui.compose.fragment

import androidx.lifecycle.ViewModel
import com.gucodero.ui.core.fragment.StatefulFragment

interface ComposableStatefulFragment<V: ViewModel>: StatefulFragment<V> {

    val viewModel: V

}