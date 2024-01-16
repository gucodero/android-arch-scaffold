package com.gucodero.ui.core.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavDirections
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.gucodero.ui.core.util.navigate
import com.gucodero.ui.core.util.onBackPressedCallback

abstract class BaseBottomSheet: BottomSheetDialogFragment() {

    private val onBackPressedCallback by onBackPressedCallback()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBackPressedCallback?.isEnabled = true
    }

    protected fun DialogFragment.show(){
        if(!isAdded){
            this.show(this@BaseBottomSheet.childFragmentManager, null)
        }
    }

    fun NavDirections.navigate(){
        this@BaseBottomSheet.navigate(this)
    }

}