package com.sergeenko.alexey.trainingdiaryapp

import androidx.compose.foundation.Box
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.ColumnScope.align
import androidx.compose.foundation.layout.ConstraintLayoutScope
import androidx.compose.foundation.layout.RowScope.align
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ConstraintLayoutScope.Splash() {
    Text(
            text = "Loading",
            modifier = constrainInParentByBias(0.5f, 0.5f)
                    .align(Alignment.CenterHorizontally)
                    .align(Alignment.CenterVertically)
    )
}
