package com.gucodero.ui.core.fragment

import androidx.lifecycle.ViewModel
import com.gucodero.ui.core.entity.ViewModelCreator

interface AppStatefulFragment<V: ViewModel> {

    fun getViewModelCreator(): ViewModelCreator<V>

}