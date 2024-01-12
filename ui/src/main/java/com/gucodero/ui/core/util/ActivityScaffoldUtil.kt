package com.gucodero.ui.core.util

import androidx.fragment.app.Fragment
import com.gucodero.ui.core.activity.ActivityScaffold
import com.gucodero.ui.core.entity.BottomNavMode
import com.gucodero.ui.core.entity.ToolbarMode

internal fun Fragment.requireActivityScaffold(): ActivityScaffold {
    val result = requireActivity()
    return if (result is ActivityScaffold) {
        result
    } else {
        throw ClassCastException("Activity does not implement ActivityScaffold.")
    }
}

fun Fragment.setToolbarMode(toolbarMode: ToolbarMode){
    requireActivityScaffold().setToolbarMode(toolbarMode)
}

fun Fragment.setBottomNavMode(bottomNavMode: BottomNavMode){
    requireActivityScaffold().setBottomNavMode(bottomNavMode)
}