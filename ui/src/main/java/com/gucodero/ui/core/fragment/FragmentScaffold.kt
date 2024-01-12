package com.gucodero.ui.core.fragment

import com.gucodero.ui.core.entity.BottomNavMode
import com.gucodero.ui.core.entity.ToolbarMode

interface FragmentScaffold {
    fun toolbarMode(): ToolbarMode = ToolbarMode.None

    fun bottomNavMode(): BottomNavMode = BottomNavMode.None

}