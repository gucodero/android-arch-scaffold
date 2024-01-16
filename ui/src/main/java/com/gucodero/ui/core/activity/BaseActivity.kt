package com.gucodero.ui.core.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment

abstract class BaseActivity: AppCompatActivity() {
    protected fun DialogFragment.show(tag: String? = null) {
        try {
            if (!isAdded) {
                show(supportFragmentManager, tag)
            }
        } catch (_: Exception) {}
    }

    protected fun DialogFragment.hide() {
        try {
            dismissAllowingStateLoss()
        } catch (_: Exception) {}
    }
}