package com.sergeenko.alexey.trainingdiaryapp

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.ColumnScope.align
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun AppBar(): @Composable (() -> Unit)? {
    return {
        TopAppBar(backgroundColor = Color.Blue) {
                Text(
                        text = "AppBar",
                        color = Color.White,
                        modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .align(Alignment.CenterHorizontally)
                )
            }
    }
}
