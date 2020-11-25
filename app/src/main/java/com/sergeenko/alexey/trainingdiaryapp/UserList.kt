/*
package com.sergeenko.alexey.trainingdiaryapp

import androidx.compose.foundation.Box
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.sergeenko.alexey.trainingdiaryapp.Compose.getFormattedDate

@ExperimentalMaterialApi
@Composable
fun TrainingList(data: List<TrainingData>, onClick: () -> Unit, onSwipe: (data: TrainingData?) -> Unit) {
    LazyColumnFor(items = data,
            contentPadding = PaddingValues(all = 5.dp)
    ) {
        TrainingItem(it, onClick, onSwipe)
    }

}

@ExperimentalMaterialApi
@Composable
private fun TrainingItem(trainingData: TrainingData, onClick: () -> Unit, onSwipe: (data: TrainingData?) -> Unit) {
    Column(
            modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
                    .drawShadow(elevation = 5.dp, opacity = 0.8f, shape = RoundedCornerShape(corner = CornerSize(5.dp)), clip = true)
                    */
/*.draggable(
                            orientation = Orientation.Horizontal,
                            onDrag = {
                                if(this.density > 5){
                                    onSwipe(trainingData)
                                }
                            }
                    )*//*

    ) {
        Box(
                modifier = Modifier.clickable(onClick = onClick),
                backgroundColor = colorResource(R.color.purple_500),
                shape = RoundedCornerShape(corner = CornerSize(5.dp)),
                padding = 10.dp,
                children = {
                    ConstraintLayout(
                            modifier = Modifier.fillMaxSize()
                    ) {
                        val (name, date) = createRefs()
                        trainingData.name?.let {
                            Text(
                                    modifier = Modifier.constrainAs(name){
                                        linkTo(start = parent.start, end = date.start, bias = 0f)
                                        linkTo(top = parent.top, bottom = parent.bottom, bias = 0.5f)
                                    },
                                    text = it,
                                    style = ItemTextStyle())
                        }
                        trainingData.date?.getFormattedDate()?.let {
                            Text(
                                    modifier = Modifier.constrainAs(date){
                                        linkTo(start = parent.start, end = parent.end, bias = 1f)
                                        linkTo(top = parent.top, bottom = parent.bottom, bias = 0.5f)
                                    },
                                    text = it,
                                    style = ItemTextStyle(),
                                    textAlign = TextAlign.End
                            )
                        }
                    }
                    trainingData.comment?.let {
                        Text(
                                text = it,
                                style = ItemTextStyle(),
                        )
                    }
                }
        )
    }

}

@Composable
private fun ItemTextStyle(): TextStyle {
    return TextStyle(
            color = colorResource(R.color.white),
            fontFamily = FontFamily.Serif,
            fontSize = TextUnit.Sp(18),
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Normal,
    )
}

*/
