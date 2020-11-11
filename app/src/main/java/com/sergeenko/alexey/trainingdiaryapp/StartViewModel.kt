package com.sergeenko.alexey.trainingdiaryapp

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.random.Random

class StartViewModel(application: Application) : BaseModel(application){
    val globalState = mutableStateOf<UserListState>(UserListState.DefaultState)
    val dismissState = mutableStateOf(false)

    fun loadTrainingList() {
        globalState.value = UserListState.LoadingState
        viewModelScope.launch(Dispatchers.IO) {
            val data = LinkedList<TrainingData>()
            for (i in 0..1000){
                data.add(
                    TrainingData(
                            name = "squat $i",
                            date = Calendar.getInstance(),
                            comment = "it was a good day $i"
                    )
                )
            }
            globalState.value = UserListState.LoadedState(data)
        }
    }

    fun changeDissmisState(b: Boolean) {
        dismissState.value = b
    }
}