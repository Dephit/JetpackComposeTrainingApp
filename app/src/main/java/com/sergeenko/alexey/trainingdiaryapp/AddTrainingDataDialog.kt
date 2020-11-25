package com.sergeenko.alexey.trainingdiaryapp

import androidx.compose.foundation.Box
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.RowScope.align
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.TextUnit
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
    ScrollableColumn(
            Modifier
                    .fillMaxSize()
                    .background(colorResource(R.color.teal_700))

    ){
        Spacer(modifier = Modifier.height(10.dp))
        MyOutlineTextField(label = "Training Name", onValueChanged = { value -> viewModel.setName(value)}, isSingleLine = true)
        MyOutlineTextField(onValueChanged = { value -> viewModel.setDate(value)}, label = "Training Date", isSingleLine = true)
        MyOutlineTextField(onValueChanged = { value -> viewModel.setComment(value)}, label = "Training Comment", isSingleLine = false)
        Spacer(modifier = Modifier.height(10.dp))
        Exercises(viewModel)
    }
}

@ExperimentalFocus
@Composable
fun Exercises(viewModel: TrainingManagement){
    with(viewModel.getExerciseListState().observeAsState()){
        Box(
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, top = 2.5.dp, bottom = 2.5.dp)
                        .background(colorResource(id = R.color.purple_500), shape = RoundedCornerShape(size = 5.dp))
        ){
            Column{
                ExerciseAddingBloc(
                    title = "Exercises",
                    onRemove = { viewModel.removeLastExercise() },
                    onAdd = { viewModel.addExercise() }
                )
                value?.forEachIndexed { index, exercise ->
                    ExerciseView(exercise)
                }
            }
        }
    }
}

@Composable
fun ExerciseAddingBloc(title: String, onAdd: ()-> Unit, onRemove: ()-> Unit ) {
    Box{
        Row{
            TitleText(title)
            Spacer(modifier = Modifier.width(145.dp))
            MiddleTextButton(onClick = onRemove, text = "Delete")
            MiddleTextButton(onClick = onAdd, text = "Add")
        }
    }
}

@Composable
fun TitleText(title: String, modifier: Modifier = Modifier.align(Alignment.CenterVertically).padding(start = 15.dp)) {
    Text(text = title,
            modifier = modifier,
            style = TextStyle(color = colorResource(id = R.color.white), fontSize = TextUnit.Sp(18))
    )
}

@Composable
fun MiddleTextButton(onClick: () -> Unit, text: String) {
    TextButton(onClick = onClick,
            Modifier
                    .padding(start = 5.dp, end = 5.dp, top = 10.dp, bottom = 10.dp)
                    .background(color = colorResource(id = R.color.purple_500), shape = RoundedCornerShape(corner = CornerSize(5.dp)))
                    .drawShadow(2.5.dp)
                    .align(Alignment.CenterVertically)
    ) {
        Text(text = text, style = TextStyle(color = colorResource(id = R.color.white), fontSize = TextUnit.Sp(18)))
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
    TextField(
            modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, top = 2.5.dp, bottom = 2.5.dp)
                    .fillMaxWidth()
                    .background(color = colorResource(id = R.color.purple_500))
                    .drawShadow(elevation = 2.5.dp),
            value = state.value,
            activeColor = colorResource(R.color.purple_700),
            inactiveColor = colorResource(R.color.purple_500),
            imeAction = if (isSingleLine) ImeAction.Done else ImeAction.NoAction,
            textStyle = TextStyle(
                    color = colorResource(R.color.white)
            ),
            label = {
                Text(text = label, color = colorResource(R.color.white))
            },
            placeholder = {
                Text(text = placeHolder, color = colorResource(R.color.white))
            },
            onValueChange = {
                onValueChanged(it)
                state.value = it
            })
}

