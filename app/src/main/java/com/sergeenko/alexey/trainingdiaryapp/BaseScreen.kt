package com.sergeenko.alexey.trainingdiaryapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import org.koin.core.qualifier.named

open class BaseScreen: Screen {

    val currentScreen = mutableStateOf<Screen?>(null)
    @Composable
    override fun get() {

    }

}
