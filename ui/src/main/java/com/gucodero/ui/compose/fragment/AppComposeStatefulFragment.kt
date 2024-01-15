package com.gucodero.ui.compose.fragment

import androidx.lifecycle.ViewModel
import com.gucodero.ui.core.fragment.AppStatefulFragment

interface AppComposeStatefulFragment<V: ViewModel>: AppStatefulFragment<V> {

    val viewModel: V

}