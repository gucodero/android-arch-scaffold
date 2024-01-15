package com.gucodero.ui.compose.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable

abstract class GlobalThemeProvider private constructor() {

    @Composable
    abstract fun GlobalTheme(content: @Composable () -> Unit)

    companion object {

        @Stable
        private val current by lazy {
            factory()
        }

        private lateinit var factory: (() -> GlobalThemeProvider)

        @Composable
        fun Theme(content: @Composable () -> Unit) {
            current.GlobalTheme {
                content()
            }
        }

        fun createTheme(
            theme: @Composable (
                content: @Composable () -> Unit
            ) -> Unit
        ) {
            if(!this::factory.isInitialized){
                factory = {
                    object: GlobalThemeProvider() {

                        @Composable
                        override fun GlobalTheme(content: @Composable () -> Unit) {
                            theme(content)
                        }

                    }
                }
            }
        }

    }


}