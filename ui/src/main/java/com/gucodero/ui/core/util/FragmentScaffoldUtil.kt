package com.gucodero.ui.core.util

import androidx.fragment.app.Fragment
import com.gucodero.ui.core.fragment.FragmentScaffold

fun Fragment.setupActivityScaffold(){
    if(this is FragmentScaffold){
        setToolbarMode(toolbarMode())
        setBottomNavMode(bottomNavMode())
    }
}