/*
package com.sergeenko.alexey.trainingdiaryapp.Compose

import androidx.compose.foundation.Box
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.sergeenko.alexey.trainingdiaryapp.Exercise
import com.sergeenko.alexey.trainingdiaryapp.R
import com.sergeenko.alexey.trainingdiaryapp.SetData

@ExperimentalFocus
@Composable
fun ExerciseView(exercise: Exercise) {
    val setsState = savedInstanceState{listOf<SetData>()}
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, top = 2.5.dp, bottom = 10.dp)
                .background(colorResource(id = R.color.purple_700), shape = RoundedCornerShape(size = 5.dp))
        ){
            Column {
                ExerciseEditTitle(savedInstanceState{exercise.name ?: ""}) { name -> exercise.setExerciseName(name) }
                ExerciseAddingBloc(
                    title = "Sets",
                    onRemove = {
                        exercise.removeLastSet()
                        setsState.updateState(exercise.sets!!)
                    },
                    onAdd = {
                        exercise.addSet(exercise.sets?.lastOrNull()?.copy() ?: SetData())
                        setsState.updateState(exercise.sets!!)
                    }
                )
                setsState.value.forEachIndexed { index, set ->
                    SetBloc(
                            weightState = savedInstanceState{
                                if(set.weight > 0) set.weight.toString() else ""
                            },
                            repState = savedInstanceState{ set.reps.toString()},
                            onWeightChanged = { weight ->
                                try {
                                    set.weight = weight.toDouble()
                                }catch (e: Exception){

                                }
                            },
                            onRepsChanged = { reps ->
                                try {
                                    set.reps = reps.toInt()
                                }catch (e: Exception){

                                }
                            }
                    )
                }
                Spacer(modifier = Modifier.padding(bottom = 10.dp))
            }
        }

}


@Composable
private fun SetBloc(onWeightChanged: (String) -> Unit, onRepsChanged: (String) -> Unit, weightState: MutableState<String>, repState: MutableState<String>) {
    val fieldWidth = 130.dp
    ConstraintLayout(
            modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 2.5.dp)
                    .background(colorResource(id = R.color.purple_700), shape = RoundedCornerShape(size = 5.dp))
                    .drawShadow(elevation = 2.5.dp,  shape = RoundedCornerShape(size = 5.dp))
    ) {
        val (weightText, repText, weightRef, repRef) = createRefs()
       TitleText(
               title = "Weight:",
               modifier = Modifier.constrainAs(weightText){
                   linkTo(start = parent.start, end = parent.end, bias = 0f)
                   linkTo(top = parent.top, bottom = parent.bottom)
               }.padding(start = 15.dp)
       )
        Box(
                modifier = Modifier
                        .width(fieldWidth)
                        .height(70.dp)
                        .padding(top = 5.dp, bottom = 5.dp)
                        .constrainAs(weightRef){
                            linkTo(start = weightText.end, end = parent.end, bias = 0f)
                            linkTo(top = parent.top, bottom = parent.bottom)
                        }
        ){
            TextField(
                    modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 2.5.dp)
                            .background(
                                    colorResource(id = R.color.white),
                                    shape = RoundedCornerShape(size = 5.dp)
                            ),
                    placeholder = { Text(text = "0") },
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.NoAction,
                    value = weightState.value,
                    activeColor = Color.Transparent,
                    textStyle = TextStyle(
                            lineHeight = TextUnit.Companion.Sp(22),
                            fontSize = TextUnit.Companion.Sp(20),
                            color = colorResource(id = R.color.black)
                    ),
                    onValueChange = {
                        if(it.length < 7){
                            it.replace(",", ".")
                            weightState.value = it
                            onWeightChanged(it)
                        }
                    })
        }
        TitleText(
                title = "Reps:",
                modifier = Modifier.constrainAs(repText){
                    linkTo(start = weightRef.end, end = parent.end, bias = 0f)
                    linkTo(top = parent.top, bottom = parent.bottom)
                }.padding(start = 15.dp)
        )
        Box(
                modifier = Modifier
                        .width(fieldWidth / 1.5f)
                        .padding(top = 5.dp, bottom = 5.dp)
                        .constrainAs(repRef){
                            linkTo(start = repText.end, end = parent.end, bias = 0f)
                            linkTo(top = parent.top, bottom = parent.bottom)
                        }
        ){
            TextField(
                    modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 2.5.dp)
                            .background(
                                    colorResource(id = R.color.white),
                                    shape = RoundedCornerShape(size = 5.dp)
                            ),
                    placeholder = { Text(text = "Reps") },
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.NoAction,
                    value = repState.value,
                    activeColor = Color.Transparent,
                    textStyle = TextStyle(
                            fontSize = TextUnit.Companion.Sp(20),
                            color = colorResource(id = R.color.black)
                    ),
                    onValueChange = {
                        if(it.length < 4){
                            it.replace(",", ".")
                            repState.value = it
                            onRepsChanged(it)
                        }
                    })
        }
    }
}

@Composable
private fun ExerciseEditTitle(value: MutableState<String>, onValueChanged: (String) -> Unit) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 2.5.dp)
            .background(
                colorResource(id = R.color.white),
                shape = RoundedCornerShape(size = 5.dp)
            ),
        placeholder = { Text(text = "Exercise") },
        label = { Text(text = "Exercise") },
        value = value.value,
        activeColor = Color.Transparent,
        textStyle = TextStyle(
            fontSize = TextUnit.Companion.Sp(20),
            color = colorResource(id = R.color.black)
        ),
        onValueChange = {
            value.value = it
            onValueChanged(it)
        })
}

private fun <T> MutableState<T>.updateState(sets: T?) {
    sets?.let {
        val list = it
        value = list
    }
}*/
