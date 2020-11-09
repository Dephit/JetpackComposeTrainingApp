package com.sergeenko.alexey.trainingdiaryapp

import androidx.compose.foundation.Box
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope.align
import androidx.compose.foundation.layout.RowScope.align
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun ErrorState(function: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text = "An error occurred",
                color = Color.Red,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .align(Alignment.CenterVertically)
            )
            Button(onClick = {
                changeState(function)
            }) {
                Text(text = "Press to load clients")
            }
        }
    }
}



