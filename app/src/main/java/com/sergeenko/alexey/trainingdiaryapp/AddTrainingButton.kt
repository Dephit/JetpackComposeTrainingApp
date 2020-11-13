package com.sergeenko.alexey.trainingdiaryapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.ConstraintLayoutScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.material.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp

@Composable
fun ConstraintLayoutScope.AddTrainingButton(onClick: ()->Unit)  {
        FloatingActionButton(
                modifier = constrainInParentByBias(
                        horizontalBias = 1f, verticalBias = 1f
                ).padding(end = 5.dp, bottom = 5.dp).preferredSize(50.dp),
                elevation = 10.dp,
                onClick = onClick,
                backgroundColor = colorResource(R.color.teal_200)
        ) {
            Image(asset = vectorResource(id = R.drawable.ic_baseline_add_24))
        }
}
