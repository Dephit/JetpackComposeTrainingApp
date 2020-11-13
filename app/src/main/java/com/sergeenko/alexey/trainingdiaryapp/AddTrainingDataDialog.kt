package com.sergeenko.alexey.trainingdiaryapp

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@ExperimentalFocus
@Composable
fun AddTrainingDialogDody(
        viewModel: TrainingManagement
) {
    ScrollableBody(viewModel)
}

@ExperimentalFocus
@Composable
fun ScrollableBody(viewModel: TrainingManagement) {
    val listState = mutableStateOf(listOf(Exercise()))
    ScrollableColumn(
            Modifier
                    .fillMaxSize()
                    .background(colorResource(R.color.teal_200))

    ){
        MyOutlineTextField(label = "Training Name", onValueChanged = { value -> viewModel.setName(value)}, isSingleLine = true)
        MyOutlineTextField(onValueChanged = { value -> viewModel.setDate(value)}, label = "Training Date", isSingleLine = true)
        MyOutlineTextField(onValueChanged = { value -> viewModel.setComment(value)}, label = "Training Comment", isSingleLine = false)
        listState.value.forEach {
            ExerciseView(it)
        }
        ExerciseBloc(listState)
    }
}

@Composable
fun ExerciseView(exercise: Exercise) {
    exercise.name?.let { Text(text = it) }
}

@Composable
fun ExerciseBloc(listState: MutableState<List<Exercise>>) {
    Row{
        FloatingActionButton(onClick = {
            val list = listState.value.toMutableList()
            list.removeLast()
            listState.value = list
        }) {
            Text(text = "-")
        }
        FloatingActionButton(onClick = {
            val list = listState.value.toMutableList()
            list.add(Exercise(name = list.size.toString()))
            listState.value = list
        }) {
            Text(text = "+")
        }
    }
}

@ExperimentalFocus
@Composable
fun MyOutlineTextField(
        placeHolder: String = "",
        onValueChanged: (String) -> Unit,
        label: String = "",
        isSingleLine: Boolean = false,
) {
    val state = savedInstanceState { "" }
    OutlinedTextField(
            modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, top = 2.5.dp, bottom = 2.5.dp)
                    .fillMaxWidth(),
            value = state.value,
            activeColor = colorResource(R.color.purple_500),
            imeAction = if (isSingleLine) ImeAction.Done else ImeAction.NoAction,
            textStyle = TextStyle(
                    color = colorResource(R.color.purple_500)
            ),
            label = {
                Text(text = label, color = colorResource(R.color.purple_500))
            },
            placeholder = {
                Text(text = placeHolder, color = colorResource(R.color.white))
            },
            onValueChange = {
                onValueChanged(it)
                state.value = it
            })
}

