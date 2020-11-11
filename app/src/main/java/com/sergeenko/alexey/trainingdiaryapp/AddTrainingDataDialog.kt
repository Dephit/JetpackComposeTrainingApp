package com.sergeenko.alexey.trainingdiaryapp

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.AlertDialog
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun AddTrainingDialog(
        onSuccess: (data: TrainingData?) -> () -> Unit = { { } },
        onClose: () -> Unit? = {}
) {
    AlertDialog(
            onDismissRequest = { onClose() },
            buttons = {
                AddTrainingDialogDody(
                        onSuccess = { onSuccess(it) },
                        onClose = { onClose() }
                )
            })
}

@Composable
fun AddTrainingDialogDody(
        onSuccess: (data: TrainingData?) -> () -> Unit = { { } },
        onClose: () -> Unit? = {}
) {
    val trainigData: TrainingData? = null
    Column {
        val textState = remember { mutableStateOf(TextFieldValue()) }
        Row() {
            OutlinedTextField(
                    value = textState.value,
                    placeholder = {
                        Text(text = "Training Name: ", )
                    },
                    onValueChange = {
                        textState.value = it
                    })
            OutlinedTextField(
                    value = textState.value,
                    placeholder = {
                        Text(text = "Training Date: ", )
                    },
                    onValueChange = {
                        textState.value = it
                    })
        }
        Row{
            TextButton(onClick = { onSuccess(trainigData) }) { Text(text = "Ok") }
            TextButton(onClick = { onClose() }) { Text(text = "Close") }
        }
    }
}