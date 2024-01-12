package com.gucodero.ui.core.activity

import com.gucodero.ui.core.entity.BottomNavMode
import com.gucodero.ui.core.entity.ToolbarMode

interface ActivityScaffold {

    fun setToolbarMode(toolbarMode: ToolbarMode)

    fun setBottomNavMode(bottomNavMode: BottomNavMode)
}