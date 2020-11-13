package com.sergeenko.alexey.trainingdiaryapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focusRequester
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@ExperimentalFocus
@Composable
fun AddTrainingDialogDody(
        onSuccess: (TrainingData?) -> Unit,
        onClose: () -> Unit? = {}
) {
    var trainingData: TrainingData?
    val nameState = savedInstanceState { "" }
    val dateState = savedInstanceState { "" }
    val commentState = savedInstanceState { "" }

    val nameRequester = FocusRequester()
    val dateFocus = FocusRequester()
    val commentRequester = FocusRequester()
    Column(
            Modifier
                    .background(colorResource(R.color.teal_200))
                    .border(
                            border = BorderStroke(1.dp, colorResource(R.color.teal_200)),
                            shape = RoundedCornerShape(5.dp)
                    )

    ){
        MyOutlineTextField(label = "Training Name", state = nameState, isSingleLine = true, focus = nameRequester, nextFocus = dateFocus)
        MyOutlineTextField(state = dateState, label = "Training Date", isSingleLine = true, focus = dateFocus, nextFocus = commentRequester)
        MyOutlineTextField(state = commentState, label = "Training Comment", isSingleLine = false, focus = commentRequester)
        Row{
            TextButton(onClick = {
                trainingData = TrainingData(
                        name = nameState.value,
                        comment = commentState.value,
                )
                onSuccess(trainingData)
            }) {
                Text(text = "Ok")
            }
            TextButton(onClick = {
                onClose()
            }) { Text(text = "Close") }
        }
    }
}

@ExperimentalFocus
@Composable
fun MyOutlineTextField(
        placeHolder: String = "",
        state: MutableState<String>,
        label: String = "",
        isSingleLine: Boolean = false,
        focus: FocusRequester = FocusRequester(),
        nextFocus: FocusRequester? = null,
) {
    OutlinedTextField(
            modifier = Modifier
                    .focusRequester(focus)
                    .padding(
                            start = 10.dp,
                            end = 10.dp,
                            top = 2.5.dp,
                            bottom = 2.5.dp,
                    ),
            value = state.value,
            activeColor = colorResource(R.color.purple_500),
            imeAction = if (isSingleLine) ImeAction.Done else ImeAction.NoAction,
            textStyle = TextStyle(
                    color = colorResource(R.color.purple_500)
            ),
            onImeActionPerformed = { imeAction: ImeAction, softwareKeyboardController: SoftwareKeyboardController? ->
                if (imeAction == ImeAction.Done) {
                    softwareKeyboardController?.hideSoftwareKeyboard()
                    nextFocus?.requestFocus() ?: focus.freeFocus()
                }
            },
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
