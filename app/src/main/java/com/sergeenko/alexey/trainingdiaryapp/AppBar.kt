package com.sergeenko.alexey.trainingdiaryapp

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.ColumnScope.align
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle

@Composable
fun AppBar(tittle: String): @Composable (() -> Unit)? {
    return {
        TopAppBar(backgroundColor = colorResource(R.color.purple_700)) {
                Text(
                        text = tittle,
                        style = TextStyle(color = colorResource(R.color.white)),
                        modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                )
            }
    }
}
