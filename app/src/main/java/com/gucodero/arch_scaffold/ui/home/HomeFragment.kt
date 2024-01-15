package com.gucodero.arch_scaffold.ui.home

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.gucodero.ui.compose.fragment.AppComposeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: AppComposeFragment.Stateful<HomeViewModel>(
    clazz = HomeViewModel::class
) {

    @Composable
    override fun Screen() {
        Text(text = "HomeFragment")
    }

    @Preview
    @Composable
    override fun ScreenPreview() {

    }
}