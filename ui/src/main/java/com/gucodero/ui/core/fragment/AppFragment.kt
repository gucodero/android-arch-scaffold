package com.gucodero.ui.core.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import com.gucodero.ui.core.util.navigate
import com.gucodero.ui.core.util.onBackPressedCallback
import com.gucodero.ui.core.util.setupActivityScaffold

abstract class AppFragment: Fragment(), FragmentScaffold {

    private val onBackPressedCallback by onBackPressedCallback()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupActivityScaffold()
        onBackPressedCallback?.isEnabled = true
    }

    protected fun DialogFragment.show(tag: String? = null){
        if(!isAdded){
            this.show(this@AppFragment.childFragmentManager, tag)
        }
    }

    fun NavDirections.navigate(){
        this@AppFragment.navigate(this)
    }

}