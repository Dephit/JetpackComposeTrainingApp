package com.sergeenko.alexey.trainingdiaryapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@ExperimentalFocus
@Composable
fun AddTrainingDialog(
        onSuccess: (TrainingData?) -> Unit,
        onClose: () -> Unit? = {}
) {
    Dialog(
            onDismissRequest = { onClose() },
    ) {
        AddTrainingDialogDody(
                onSuccess =  {trainingData ->  onSuccess(trainingData) }
        ) { onClose() }
    }
}

@ExperimentalFocus
@Composable
fun AddTrainingDialogDody(
        onSuccess: (TrainingData?) -> Unit,
        onClose: () -> Unit? = {}
) {
    var trainingData: TrainingData?
    val nameState = remember { mutableStateOf(TextFieldValue()) }
    val dateState = remember { mutableStateOf(TextFieldValue()) }
    val commentState = remember { mutableStateOf(TextFieldValue()) }
    Column(
            Modifier
                    .background(colorResource(R.color.teal_200))
                    .border(
                            border = BorderStroke(1.dp, colorResource(R.color.teal_200)),
                            shape = RoundedCornerShape(5.dp)
                    )

    ){
        MyOutlineTextField(label = "Training Name", state = nameState, isSingleLine = true)
        MyOutlineTextField(label = "Training Date", state = dateState, isSingleLine = true)
        MyOutlineTextField(label = "Training Comment", state = commentState, isSingleLine = false)
        Row{
            TextButton(onClick = {
                trainingData = TrainingData(
                        name = nameState.value.text,
                        comment = commentState.value.text,
                )
                onSuccess(trainingData)
            }) { Text(text = "Ok") }
            TextButton(onClick = { onClose() }) { Text(text = "Close") }
        }
    }
}

@ExperimentalFocus
@Composable
fun MyOutlineTextField(
        placeHolder: String = "",
        state: MutableState<TextFieldValue>,
        label: String = "",
        isSingleLine: Boolean = false,
) {
    OutlinedTextField(
            modifier = Modifier
                    .padding(
                            start = 10.dp,
                            end = 10.dp,
                            top = 2.5.dp,
                            bottom = 2.5.dp,
                    ),
            value = state.value,
            activeColor = colorResource(R.color.purple_500),
            imeAction = if (isSingleLine) ImeAction.Done else ImeAction.Unspecified,
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
                state.value = it
            })
}
