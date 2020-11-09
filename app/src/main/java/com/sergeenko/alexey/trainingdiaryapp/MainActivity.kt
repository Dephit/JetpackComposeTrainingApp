package com.sergeenko.alexey.trainingdiaryapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ColorToVectorConverter
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.stateFor
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val viewModel: StartViewModel by inject()

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val dismissState = mutableStateOf(true)
            Scaffold(
                topBar = AppBar(),
                bodyContent = {
                    when (viewModel.globalState.value) {
                        is UserListState.DefaultState -> DefaultState { viewModel.loadTrainingList() }
                        is UserListState.LoadedState -> UserList().trainingList(
                            data = (viewModel.globalState.value as UserListState.LoadedState).list,
                            state = viewModel.globalState
                        )
                        is UserListState.LoadingState -> Splash()
                        UserListState.ErrorState -> ErrorState { viewModel.loadTrainingList() }
                    }
                    if(dismissState.value)
                        AlertDialog(
                        onDismissRequest = {
                            dismissState.value = false
                        },
                        buttons = {
                            Row() {
                                TextButton(onClick = {},) { Text(text = "Ok") }
                                TextButton(onClick = {},) { Text(text = "Close") }
                            }
                        })
                }
            )
        }
    }
}

fun Calendar.getFormattedDate(): String{
    val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.CANADA)
    return dateFormat.format(time)
}

