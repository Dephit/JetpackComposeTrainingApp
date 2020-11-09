package com.sergeenko.alexey.trainingdiaryapp

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.savedinstancestate.rememberSavedInstanceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import java.util.*

class UserList{

    @Composable
    fun trainingList(data: LinkedList<TrainingData>, state: MutableState<UserListState>) {
        ScrollableColumn{
            data.forEach {
                singleTrainingItem(it, state)
            }
        }
    }

    @Composable
    private fun singleTrainingItem(trainingData: TrainingData, state: MutableState<UserListState>) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Box(
                padding = 5.dp,
                paddingBottom = 2.5.dp,
            ){
                Box(
                    modifier = Modifier.clickable(onClick = {
                        state.value = UserListState.ErrorState
                    },

                        ),
                    backgroundColor = Color.Blue,
                    border = BorderStroke(2.dp, Color.Magenta),
                    shape = RoundedCornerShape(corner = CornerSize(5.dp)),
                    padding = 10.dp,
                    children = {
                        Row(modifier = Modifier.fillMaxWidth().align(Alignment.Start)) {
                            trainingData.name?.let {
                                Text(
                                    text = it,
                                    style = itemTextStyle()
                                )
                            }
                            trainingData.date?.getFormattedDate()?.let {
                                Text(
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
    }

    private fun itemTextStyle(): TextStyle {
        return TextStyle(
            color = Color.White,
            fontFamily = FontFamily.Serif,
            fontSize = TextUnit.Sp(18),
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Normal,
        )
    }
}
