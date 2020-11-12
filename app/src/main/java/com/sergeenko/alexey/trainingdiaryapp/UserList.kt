package com.sergeenko.alexey.trainingdiaryapp

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.ScrollableController
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.lazy.LazyColumnForIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.savedinstancestate.rememberSavedInstanceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.gesture.ScrollCallback
import androidx.compose.ui.gesture.scrollGestureFilter
import androidx.compose.ui.gesture.scrollorientationlocking.Orientation
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import java.util.*
import kotlin.system.measureTimeMillis

class UserList{

    @Composable
    fun trainingList(data: List<TrainingData>, state: MutableState<UserListState>) {
        LazyColumnFor(items = data,
                contentPadding = PaddingValues(all = 5.dp)
        ) {
            singleTrainingItem(it, state)
        }

    }

    @Composable
    private fun singleTrainingItem(trainingData: TrainingData, state: MutableState<UserListState>) {
        Column(
                modifier = Modifier.fillMaxWidth().padding(
                        bottom = 5.dp
                ).drawShadow(elevation = 5.dp, opacity = 0.8f, shape = RoundedCornerShape(corner = CornerSize(5.dp)), clip = true),
        ) {
            Box(
                    modifier = Modifier.clickable(onClick = { state.value = UserListState.ErrorState }),
                    backgroundColor = colorResource(R.color.purple_500),
                    //border = BorderStroke(2.dp, colorResource(R.color.purple_700)),
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
                                        style = itemTextStyle())
                            }
                            trainingData.date?.getFormattedDate()?.let {
                                Text(
                                        modifier = Modifier.constrainAs(date){
                                            linkTo(start = parent.start, end = parent.end, bias = 1f)
                                            linkTo(top = parent.top, bottom = parent.bottom, bias = 0.5f)
                                        },
                                        text = it,
                                        style = itemTextStyle(),
                                        textAlign = TextAlign.End
                                )
                            }
                        }
                        trainingData.comment?.let {
                            Text(
                                    text = it,
                                    style = itemTextStyle(),
                            )
                        }
                    }
            )
        }

    }

    @Composable
    private fun itemTextStyle(): TextStyle {
        return TextStyle(
            color = colorResource(R.color.white),
            fontFamily = FontFamily.Serif,
            fontSize = TextUnit.Sp(18),
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Normal,
        )
    }
}
