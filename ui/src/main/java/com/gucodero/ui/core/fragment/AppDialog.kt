package com.gucodero.ui.core.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavDirections
import com.gucodero.ui.core.util.navigate
import com.gucodero.ui.core.util.onBackPressedCallback

abstract class AppDialog: DialogFragment() {

    private val onBackPressedCallback by onBackPressedCallback()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBackPressedCallback?.isEnabled = true
    }

    /**
     * Muestra el Diálogo.
     */
    protected fun DialogFragment.show(){
        if(!isAdded){
            this.show(this@AppDialog.childFragmentManager, null)
        }
    }

    /**
     * Extensión de [NavDirections] que permite navegar utilizando las configuraciones de navegación
     * definidas en el Diálogo actual.
     */
    fun NavDirections.navigate(){
        this@AppDialog.navigate(this)
    }

}