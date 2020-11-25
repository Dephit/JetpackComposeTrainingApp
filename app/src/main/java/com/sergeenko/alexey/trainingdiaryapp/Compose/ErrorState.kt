/*
package com.sergeenko.alexey.trainingdiaryapp.Compose

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope.align
import androidx.compose.foundation.layout.ConstraintLayoutScope
import androidx.compose.foundation.layout.RowScope.align
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun ConstraintLayoutScope.ErrorState(function: () -> Unit) {
    Column(
            modifier = constrainInParentByBias(0.5f, 0.5f)
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
        Button(onClick = function) {
            Text(text = "Press to load clients")
        }
    }
}



*/
