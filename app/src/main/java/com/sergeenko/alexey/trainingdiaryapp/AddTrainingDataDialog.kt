package com.sergeenko.alexey.trainingdiaryapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.ColumnScope.align
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@ExperimentalFocus
@Composable
fun AddTrainingDialog(
        onSuccess: (data: TrainingData?) -> () -> Unit = { { } },
        onClose: () -> Unit? = {}
) {
    Dialog(
            onDismissRequest = { onClose() },
    ) {
        AddTrainingDialogDody(
                onSuccess = { onSuccess(it) },
                onClose = { onClose() }
        )
    }
}

@ExperimentalFocus
@Composable
fun AddTrainingDialogDody(
        onSuccess: (data: TrainingData?) -> () -> Unit = { { } },
        onClose: () -> Unit? = {}
) {
    val trainingData: TrainingData? = null
    val nameState = remember { mutableStateOf(TextFieldValue()) }
    val dateState = remember { mutableStateOf(TextFieldValue()) }
    val commentState = remember { mutableStateOf(TextFieldValue()) }
    Column(
            Modifier
                    .background(Color.White/*(R.color.light_blue)*/).border(BorderStroke(2.dp, Color.Black))

    ){
        MyOutlineTextField(label = "Training Name", state = nameState, isSingleLine = true)
        MyOutlineTextField(label = "Training Date", state = dateState, isSingleLine = true)
        MyOutlineTextField(label = "Training Comment", state = commentState, isSingleLine = false)
        Row{
            TextButton(onClick = { onSuccess(trainingData) }) { Text(text = "Ok") }
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
            activeColor = Color.Blue,
            imeAction = if(isSingleLine) ImeAction.Done else ImeAction.Unspecified,
            label = {
                Text(text = label)
            },
            placeholder = {
                Text(text = placeHolder)
            },
            onValueChange = {
                state.value = it
            })
}
