package com.gucodero.ui.core.lifecycle.base

interface ViewModelLoadable {

    var isLoading: Boolean

    suspend fun onLoading(listener: (isLoading: Boolean) -> Unit)

}